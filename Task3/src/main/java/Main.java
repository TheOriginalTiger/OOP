public class Main {
    public static void main(String args[])
    {
        Stack<Integer> s = new Stack<>(1);
        for(int i = 0; i < 5; i++)
            s.push(i);
        s.pop();
        s.push(228);
        for (Integer heh : s)
        {
            System.out.println(heh);
        }
    }
}
