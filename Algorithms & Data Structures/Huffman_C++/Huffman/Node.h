#pragma once

class Node 
{
public:
	Node(long long frequency);
	Node(Node* leftSon, Node* rightSon);
	Node* getLeftSon();
	Node* getRightSon();
	void setDepth(int depth);
	long long getFrequency();
	int getDepth();
	void setLeftSon(Node* leftSon);
	void setRightSon(Node* righttSon);
private:
	int depth;
	long long frequency;
	Node* leftSon;
	Node* rightSon;
};