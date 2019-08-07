package com.bjs.java8.part14;

/**
 * @Description
 * @Author BianJiashuai
 */
public class PersistentTree {

    public static void main(String[] args) {
        Tree t1 = getTree();
        Tree t2 = getTree();
        Tree t3 = getTree();

        // found = 23
        System.out.println(lookup("Raoul", -1, t1));

        System.out.println("-------------------------------");

        System.out.println("修改前: " + t1);
        int update = update("Raoul", 100, t1);          // 会将原来的树中Raoul的值改为100
        System.out.println("返回值: " + update);
        System.out.println("修改后: " + t1);

        System.out.println("-------------------------------");

        System.out.println("修改前: " + t2);
        Tree update2 = update2("Mary", 200, t2);        // 会将原来的树中Mary的值改为200
        System.out.println("返回值: " + update2);
        System.out.println("修改后: " + t2);

        System.out.println("-------------------------------");

        System.out.println("修改前: " + t3);
        Tree funUpdate = funUpdate("Emily", 500, t3);   // 返回一个全新的对象冰将Emily值改为500，原来的树不变
        System.out.println("返回值: " + funUpdate);
        System.out.println("修改后: " + t3);
    }

    public static Tree getTree() {
        return new Tree("Mary", 22,
                new Tree("Emily", 20,
                        new Tree("Alan", 50, null, null),
                        new Tree("Georgie", 23, null, null)
                ),
                new Tree("Tran", 29,
                        new Tree("Raoul", 23, null, null),
                        null
                )
        );
    }

    static class Tree {
        private String key;
        private int val;
        private Tree left, right;

        public Tree(String key, int val, Tree left, Tree right) {
            this.key = key;
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("[");
            sb.append("key=").append(key).append(", ");
            sb.append("val=").append(val).append(", ");
            sb.append("left=").append(left == null ? "null" : left).append(", ");
            sb.append("right=").append(right == null ? "null" : right);
            sb.append("]");
            return sb.toString();
        }
    }

    /**
     * 查找对应key的值
     *
     * @param key          查找的key
     * @param defaultValue 默认值
     * @param t            查找的tree
     * @return 如果找到返回对应key的值，否则返回defaultValue
     */
    public static int lookup(String key, int defaultValue, Tree t) {
        if (t == null || key == null) {
            return defaultValue;
        } else if (key.equals(t.key)) {
            return t.val;
        } else {
            return lookup(key, defaultValue, key.compareTo(t.key) < 0 ? t.left : t.right);
        }
    }

    // 【注意】对现有的树进行修改，这意味着使用树存放映射关系的所有用户都会感知到这些修改
    public static int update(String key, int newVal, Tree t) {
        if (t == null || key == null) {
            throw new RuntimeException("key or t is null");
        } else if (key.equals(t.key)) {
            t.val = newVal;
            return 1;
        } else {
            return update(key, newVal, key.compareTo(t.key) < 0 ? t.left : t.right);
        }
    }

    // 【注意】对现有的树进行修改，这意味着使用树存放映射关系的所有用户都会感知到这些修改
    public static Tree update2(String key, int newVal, Tree t) {
        if (key == null) {
            throw new RuntimeException("key is null");
        } else if (t == null) {
            return new Tree(key, newVal, null, null);
        } else if (key.equals(t.key)) {
            t.val = newVal;
        } else if (key.compareTo(t.key) < 0) {
            t.left = update2(key, newVal, t.left);
        } else {
            t.right = update2(key, newVal, t.right);
        }
        return t;
    }

    // 采用函数式方法，不会对现有的树进行修改
    public static Tree funUpdate(String key, int newVal, Tree t) {
        if (key == null) {
            throw new RuntimeException("key is null");
        } else {
            return (t == null) ? new Tree(key, newVal, null, null)
                    : (key.equals(t.key)) ?
                        new Tree(key, newVal, t.left, t.right)
                    : (key.compareTo(t.key) < 0) ?
                        new Tree(t.key, t.val, funUpdate(key, newVal, t.left), t.right)
                    : new Tree(t.key, t.val, t.left, funUpdate(key, newVal, t.right));
        }
    }

}
