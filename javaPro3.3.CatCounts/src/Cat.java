
public class Cat {
    private Double originWeight;
    private Double weight;

    private Double minWeight;
    private Double maxWeight;

    private boolean alive;
    private static Integer count = 0;

    public Cat() {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        minWeight = 1000.0;
        maxWeight = 9000.0;
        alive = true;
        count += 1;
    }

    public void meow() {
        weight = weight - 1;
        System.out.println("Meow");
    }

    public void feed(Double amount) {
        weight = weight + amount;
    }

    public void drink(Double amount) {
        weight = weight + amount;
    }

    public Double getWeight() {
        return weight;
    }

    public static Integer getCount() {
        return count;
    }

    public void setDead() {
        if (alive) {
            alive = false;
            count -= 1;
        }
    }

    public String getStatus() {
        if (weight < minWeight) {
            setDead();
            return "Dead";
        } else if (weight > maxWeight) {
            setDead();
            return "Exploded";
        } else if (weight > originWeight) {
            return "Sleeping";
        } else {
            return "Playing";
        }
    }
}