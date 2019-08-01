package com.bjs.java8.part07;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @Description
 * @Author BianJiashuai
 */
public class Demo03_Spliterator {

    public static int countWordsIteratively(String s) {
        int count = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                lastSpace = true;
            } else {
                if (lastSpace) {
                    count++;
                }
                lastSpace = false;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        final String SENTENCE =
                " Nel  mezzo del cammin di nostra vita " +
                        "mi ritrovai in una selva oscura" +
                        " ché la dritta via era smarrita ";
        System.out.println("Found " + countWordsIteratively(SENTENCE) + " words");
        System.out.println(SENTENCE.trim().split("[\\s]+").length);
        System.out.println("-------------------------------");
        System.out.println(countWords(getStream(SENTENCE)));

        // 并行会出问题，因为随机拆分会导致将一个单词拆分成两个单词
        // 解决：必须为Character实现一个Spliterator接口，让它只能在两个词之间拆开String,代码如WordCounterSpliterator
        System.out.println(countWords(getStream(SENTENCE).parallel()));
        System.out.println("-----------------上面错误值的解决如下---------------------");
        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        System.out.println(countWords(StreamSupport.stream(spliterator, true)));
    }

    public static Stream<Character> getStream(String str) {
        return IntStream.range(0, str.length()).mapToObj(str::charAt);
    }

    public static int countWords(Stream<Character> stream) {
        return stream.reduce(new WordCounter(0, true),
                WordCounter::accumulate, WordCounter::combine).getCount();
    }
}

class WordCounter {
    private final int count;
    private final boolean lastSpace;

    public WordCounter(int count, boolean lastSpace) {
        this.count = count;
        this.lastSpace = lastSpace;
    }

    public WordCounter accumulate(Character c) {
        if (Character.isSpaceChar(c)) {
            // 当前字符是空格
            return lastSpace ? this : new WordCounter(count, true);
        } else  {
            return lastSpace ? new WordCounter(count + 1, false) : this;
        }
    }

    public WordCounter combine(WordCounter wordCounter) {
        return new WordCounter(wordCounter.count + count, wordCounter.lastSpace);
    }

    public int getCount() {
        return count;
    }
}

class WordCounterSpliterator implements Spliterator<Character> {

    private final String string;
    private int currentChar = 0;

    public WordCounterSpliterator(String string) {
        this.string = string;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        action.accept(string.charAt(currentChar++));    // 处理当前字符
        return currentChar < string.length();           // 如果还有字符则返回true
    }

    /**
     * 定义了拆分要遍历的数据结构的逻辑。
     * 就像在代码清单7-1中实现的 RecursiveTask 的 compute 方法一样（分支/合并框架的使用方式）
     */
    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = string.length() - currentChar;
        if (currentSize < 10) {
            // 如果剩余的Character数量低于下限（这里定义的是10），你就返回null表示无需进一步拆分
            return null;
        }
        for (int splitPos = currentSize / 2 + currentChar; splitPos < string.length(); splitPos++) {
            if (Character.isSpaceChar(string.charAt(splitPos))) {
                /**
                 * 必须是空格才拆分，防止一个单词拆成两个。
                 * 一旦找到了适当的拆分位置，就可以创建一个新的 Spliterator 来遍历从当前位置到拆分位置的子串；
                 * 把当前位置 this 设为拆分位置，因为之前的部分将由新Spliterator 来处理
                 */
                Spliterator<Character> spliterator =
                        new WordCounterSpliterator(string.substring(currentChar, splitPos));
                currentChar = splitPos;
                return spliterator;
            }
        }
        return null;
    }

    @Override
    public long estimateSize() {
        /**
         * 还需要遍历的元素（总长度 - 已经遍历过的）
         */
        return string.length() - currentChar;
    }

    @Override
    public int characteristics() {
        /**
         * 告诉框架这个Spliterator是ORDERED（顺序就是String中各个Character的次序）、SIZED（estimatedSize方法的返回值是精确的）、
         * SUBSIZED（trySplit方法创建的其他Spliterator也有确切大小）、NONNULL（String中不能有为null的Character）和
         * IMMUTABLE（在解析String时不能再添加Character因为String本身是一个不可变类）
         */
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }
}
