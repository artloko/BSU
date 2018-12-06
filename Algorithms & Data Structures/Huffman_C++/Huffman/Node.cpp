#include "Node.h"


//class Node
//{
//	Node(long long frequency)
//	{
//		this->setLeftSon(nullptr);
//		this->setRightSon(nullptr);
//
//	}
//	Node(Node leftSon, Node rightSon);
//	Node getLeftSon();
//	Node getRightSon();
//	void setDepth(int depth);
//	long long getFrequency();
//	int getDepth();
//	void setLeftSon(Node* leftSon)
//	{
//		this->
//	}
//	void setRightSon(Node* righttSon);
//};

Node::Node(long long _frequency)
{
	leftSon = rightSon = nullptr;
	frequency = _frequency;
	depth = 0;
}

Node::Node(Node* _leftson, Node* _rightSon)
{
	leftSon = _leftson;
	rightSon = _rightSon;
	frequency = _leftson->frequency + _rightSon->frequency;
	depth = 0;
}

Node* Node::getLeftSon()
{
	return leftSon;
}

Node* Node::getRightSon()
{
	return rightSon;
}

void Node::setDepth(int _depth)
{
	depth = _depth;
}

long long Node::getFrequency()
{
	return frequency;
}

int Node::getDepth()
{
	return depth;
}

void Node::setLeftSon(Node* _leftSon)
{
	leftSon = _leftSon;
}

void Node::setRightSon(Node* _rightSon)
{
	leftSon = _rightSon;
}


