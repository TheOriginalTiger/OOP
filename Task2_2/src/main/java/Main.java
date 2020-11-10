public class Main {
    @SuppressWarnings("unchecked")
    public static void main(String agrs[])
    {
        PriorityQueue <String> q = new PriorityQueue<>(10);
        q.insert(new Pair<Integer, String>(10, "Rofler"));
        q.insert(new Pair<Integer, String>(19, "Rofle"));
        q.insert(new Pair<Integer, String>(20, "Rofl"));
        q.insert(new Pair<Integer, String>(30, "Rof"));
        System.out.println(q.stream().count());
    }
}
