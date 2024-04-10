import java.io.*;

public class RobotPartPersister {

    public void serialisePart(RobotPart aPart) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter("Store.txt"));

        writer.write(aPart.name);
        writer.newLine();

        writer.write(aPart.description);
        writer.newLine();

        writer.write(aPart.supplier);
        writer.newLine();

        writer.write(aPart.weight);
        writer.newLine();

        writer.close();
    }

    public RobotPart deserialisePart() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("Store.txt"));

        RobotPart part = new RobotPart();
        part.name = reader.readLine();
        part.description = reader.readLine();
        part.supplier = reader.readLine();
        part.weight = Integer.parseInt(reader.readLine());

        reader.close();

        return part;
    }
}