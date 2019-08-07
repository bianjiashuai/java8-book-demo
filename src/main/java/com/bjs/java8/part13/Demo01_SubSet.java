package com.bjs.java8.part13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Description
 * @Author BianJiashuai
 */
public class Demo01_SubSet {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 4, 9);
        System.out.println(list);
        System.out.println(subSets(list));
    }

    public static List<List<Integer>> subSets(List<Integer> list) {
        if (list.isEmpty()) {
            List<List<Integer>> ans = new ArrayList<>();
            ans.add(Collections.EMPTY_LIST);
            return ans;
        }

        Integer first = list.get(0);
        List<Integer> rest = list.subList(1, list.size());
        List<List<Integer>> subans = subSets(rest);
        List<List<Integer>> subans2 = insertAll(first, subans);
        return concat(subans, subans2);

        /**
         * 1, 4, 9      1       4, 9
         * 4, 9         4,      9
         * 9            9,      []
         *
         */
    }

    private static List<List<Integer>> concat(List<List<Integer>> subans, List<List<Integer>> subans2) {
//        subans.addAll(subans2);
//        return subans;

        // 【强烈推荐】 concat是纯粹的函数式。虽然它在内部会对对象进行修改（向列表r添加元素）, 但是它返回的结果基于参数却没有修改任何一个传入的参数
        List<List<Integer>> r = new ArrayList<>(subans);
        r.addAll(subans2);
        return r;

    }

    private static List<List<Integer>> insertAll(Integer first, List<List<Integer>> lists) {
        List<List<Integer>> result = new ArrayList<>();
        for (List<Integer> list : lists) {
            List<Integer> copyList = new ArrayList<>();
            copyList.add(first);
            copyList.addAll(list);
            result.add(copyList);
        }
        return result;
    }
}
