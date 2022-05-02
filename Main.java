public class Main {
    public static void main(String[] args)
    {
        DataGenerator generator = new DataGenerator();

        GUI frame = new GUI("Diary", generator.getGroup());
        frame.setVisible(true);
    }
}
