package main

import "fmt"

type Node struct {
	key   int
	left  *Node
	right *Node
}

func rightRotate(x *Node) *Node {
	y := x.left
	x.left = y.right
	y.right = x
	return y
}

func leftRotate(x *Node) *Node {
	y := x.right
	x.right = y.left
	y.left = x
	return y
}

func splay(root *Node, key int) *Node {
	if root == nil || root.key == key {
		return root
	}

	if root.key > key {
		if root.left == nil {
			return root
		}

		if root.left.key > key {
			root.left.left = splay(root.left.left, key)
			root.left = rightRotate(root.left)
		} else if root.left.key < key {
			root.left.right = splay(root.left.right, key)
			if root.left.right != nil {
				root.left = leftRotate(root.left)
			}
		}

		return rightRotate(root)

	} else {
		if root.right == nil {
			return root
		}

		if root.right.key > key {
			root.right.left = splay(root.right.left, key)
			if root.right.left != nil {
				root.right = rightRotate(root.right)
			}
		} else if root.right.key < key {
			root.right.right = splay(root.right.right, key)
			root.right = leftRotate(root.right)
		}

		return leftRotate(root)

	}
}

func insert(root *Node, key int) *Node {
	if root == nil {
		return &Node{key: key}
	}

	root = splay(root, key)

	if root.key == key {
		return root
	}

	newNode := &Node{key: key}

	if root.key > key {
		newNode.right = root
		newNode.left = root.left
		root.left = nil
	} else {
		newNode.left = root
		newNode.right = root.right
		root.right = nil
	}

	return newNode
}

func preOrder(root *Node) {
	if root != nil {
		fmt.Printf("%d ", root.key)
		preOrder(root.left)
		preOrder(root.right)
	}
}

func main() {
	var root *Node
	keys := []int{1, 5, 9, 15, 18, 6}

	for _, key := range keys {
		root = insert(root, key)
	}

	search := []int{1, 15, 5}

	for _, x := range search {
		root = splay(root, x)
	}

	preOrder(root)
}
