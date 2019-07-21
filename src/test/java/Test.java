import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Test {
    public static void main(String[] args) {
        PriorityQueue<TestData> q = new PriorityQueue<>(Comparator.comparingInt(TestData::getData));

        input(q);

        print(q);

        input(q);

        TestData td = q.poll();
        td.setData(3);
        q.add(td);

        print(q);

        input(q);

        System.out.println("==============");

        for (TestData testData : q) {
            System.out.println(testData);
        }

        System.out.println("==============");
        List<TestData> l = new ArrayList<>(q);
        for (TestData testData : l) {
            System.out.println(testData);
        }
    }

    private static void print(PriorityQueue<TestData> q) {
        while(!q.isEmpty()) {
            System.out.println(q.poll());
        }
    }

    private static void input(PriorityQueue<TestData> q) {
        q.add(new TestData(5));
        q.add(new TestData(4));
        q.add(new TestData(1));
        q.add(new TestData(2));
        q.add(new TestData(3));
        q.add(new TestData(6));
    }

    static class TestData {
        int data;

        public TestData(int data) {
            this.data = data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        @Override
        public String toString() {
            return String.valueOf(data);
        }
    }
}
