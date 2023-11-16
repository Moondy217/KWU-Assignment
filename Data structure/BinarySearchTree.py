class BST: #트리 선언
    def __init__(self, key):
        self.key = key
        self.left = None
        self.right = None

class BinaryTree:
    def __init__(self):
        self.root = None

    def insert(self, key): #요소 삽입
        self.root = self.insert2(self.root, key)
        return self.root is not None

    def insert2(self, node, key): #삽입 시 왼쪽, 오른쪽 key값 크기 구별하여 삽입
       if node is None:
           return BST(key)

       else:
           if key < node.key:
               node.left = self.insert2(node.left, key)
           else:
               node.right = self.insert2(node.right, key)

       return node

    def InOrderTraversal(self):

        def InOrderTraversal2(root):

            if root is None:
                pass
            else:
                global inorder_list, result, length
                inorder_list = [] #key값 저장 리스트

                InOrderTraversal2(root.left)

                result = sum(inorder_list)
                length = len(inorder_list)

                for i in range(len(TreeNum)): #중위 탐색 시 L:30이상, R:70이하 key값만 저장
                    root.key = TreeNum[i]
                    if root.key >= 30 and root.key <= 70:
                        inorder_list.append(root.key)

                InOrderTraversal2(root.right)

        InOrderTraversal2(self.root)



TreeNum = [44, 88, 17, 32, 65, 97, 8, 82, 93, 28, 54, 29, 76, 80]

Tree = BinaryTree()
for x in TreeNum:
    Tree.insert(x)
Tree.InOrderTraversal()


inorder_list = inorder_list[:length]

Str = ' + '.join([str(n) for n in inorder_list]) #수식 출력을 위해 문자열로 변환
print('(', Str, ')', '=', result/length)
