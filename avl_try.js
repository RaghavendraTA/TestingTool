let createNode = (data) => {
    return {
        'data': data,
        'depth': 0,
        'left': null,
        'right': null
    }
};

class AVLTree {

    constructor() {
        this.root = null;
    }

    getHeight(node) {
        if (node == null) return 0;
        else return node.depth;
    }

    getBalanceFactor(node) {
        return this.getHeight(node.right) - this.getHeight(node.left);
    }

    update_depth(node) {
        node.depth = Math.max(this.getHeight(node.right), this.getHeight(node.left)) + 1;
    }

    l_rotate(node) {
        let rightNode = node.right;
        node.right = rightNode.left;
        rightNode.left = node;
        this.update_depth(node);
        this.update_depth(rightNode);
        return rightNode;
    }

    r_rotate(node) {
        let leftNode = node.left;
        node.left = leftNode.right;
        leftNode.right = node;
        this.update_depth(node);
        this.update_depth(leftNode);
        return leftNode;
    }

    adjustBalance(node, ) {
        if (node == null)
            return node;

        let balanceFactor = this.getBalanceFactor(node);

        if (balanceFactor == -2) {
            if (this.getBalanceFactor(node.left) == 1)
                node.left = this.l_rotate(node.left);
            return this.r_rotate(node);
        } else if (balanceFactor == 2) {
            if (this.getBalanceFactor(node.right) == -1)
                node.right = this.r_rotate(node.right);
            return this.l_rotate(node);
        }

        return node;
    }

    insert(node, data) {
        if (node == null) 
            return createNode(data);
        if (data <= node.data)
            node.left = this.insert(node.left, data);
        else
            node.right = this.insert(node.right, data);
        this.update_depth(node);
        return this.adjustBalance(node);
    }

    add(data) {
        this.root = this.insert(this.root, data);
    }
}

var tree = new AVLTree();
tree.add(10);
tree.add(9);
tree.add(20);
tree.add(8);
tree.add(7);
tree.add(6);
tree.add(5);
tree.add(4);
tree.add(3);
tree.add(2);
tree.add(1);
console.log("%j", tree.root);
