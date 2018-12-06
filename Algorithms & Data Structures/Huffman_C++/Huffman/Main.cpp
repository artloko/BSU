#include "Node.h"
#include <fstream>
#include <queue>

static long long result = 0;

void rightLeft(Node* node, int depth)
{
	if (node != nullptr)
	{
		depth++;
		rightLeft(node->getLeftSon(), depth);
		rightLeft(node->getRightSon(), depth);
		if (node->getLeftSon() == nullptr && node->getRightSon() == nullptr)
		{
			node->setDepth(depth);
			result += (node->getDepth() * node->getFrequency());
		}
	}
}

void main()
{
	std::ifstream fin("huffman.in");
	std::ofstream fout("huffman.out");

	int n = 0;
	int temp = 0;
	fin >> n;
	std::queue<Node*> primeQueue;
	std::queue<Node*> tempQueue;
	for (int i = 0; i < n; i++)
	{
		fin >> temp;
		primeQueue.push(new Node(temp));
	}
	Node* leftSon = primeQueue.front();
	primeQueue.pop();
	Node* rightSon = primeQueue.front();
	primeQueue.pop();
	Node* newNode = new Node(leftSon, rightSon);
	tempQueue.push(newNode);
	while (!primeQueue.empty())
	{
		if (primeQueue.size() && primeQueue.front()->getFrequency() < tempQueue.front()->getFrequency())
		{
			leftSon = primeQueue.front();
			primeQueue.pop();
		}
		else
		{
			leftSon = tempQueue.front();
			tempQueue.pop();
		}
		if (!tempQueue.size())
		{
			rightSon = primeQueue.front();
			primeQueue.pop();
		}
		else if (primeQueue.size() && primeQueue.front()->getFrequency() < tempQueue.front()->getFrequency())
		{
			rightSon = primeQueue.front();
			primeQueue.pop();
		}
		else
		{
			rightSon = tempQueue.front();
			tempQueue.pop();
		}
		newNode = new Node(leftSon, rightSon);
		tempQueue.push(newNode);
	}
	while (tempQueue.size() != 1)
	{
		leftSon = tempQueue.front();
		tempQueue.pop(); 
		rightSon = tempQueue.front();
		tempQueue.pop(); 
		newNode = new Node(leftSon, rightSon);
		tempQueue.push(newNode);
	}
	rightLeft(tempQueue.front(), -1);
	fout << result;
	fin.close();
	fout.close();
}
