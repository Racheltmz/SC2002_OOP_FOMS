import java.io.IOException;

public class RobotPart {

    public String name;
    public String description;
    public String supplier;
    public int weight;

    @Override
    public String toString() {
        return "RobotComponent [name=" + name + ", description=" + description + ", supplier=" + supplier + ", weight=" + weight + "]";
    }
    public static void main(String[] args) throws IOException {

        RobotPart sharklaseradapter = new RobotPart();
        sharklaseradapter.name = "Shark Laser Adapter";
        sharklaseradapter.description = "Collar and fittings for mounting laser on shark. Shark not included";
        sharklaseradapter.supplier = "Dr Nono";
        sharklaseradapter.weight = 5000;

        RobotPartPersister persister = new RobotPartPersister();
        persister.serialisePart(sharklaseradapter);

        RobotPart storedpart = persister.deserialisePart();

        if (!sharklaseradapter.toString().equals(storedpart.toString())) throw
                new RuntimeException("Store failed! "+storedpart+" does not match "+sharklaseradapter);

    }
}