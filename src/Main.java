public class Main {


    public static void main(String[] args) {
        // Constants for the length of the base and secondary segments in inches
        final double BASE_LENGTH = Math.sqrt(2)/2;
        final double SECONDARY_LENGTH = Math.sqrt(2)/2;

// Desired end-effector position (x,y) in inches
        double x = 1.2;
        double y = 0.6;

// Compute the distance between the origin and the end-effector
        double r = Math.sqrt(x * x + y * y);

// Check if the end-effector is within reach of the arm
        if (r > BASE_LENGTH + SECONDARY_LENGTH) {
            System.out.println("Target position is out of reach!");
            return;
        }

// Compute the angle of the base segment
        double theta1 = Math.atan2(y, x);

// Limit angle 1 to 60 degrees in either direction
        if (theta1 < -Math.PI / 3.0) {
            theta1 = -Math.PI / 3.0;
        } else if (theta1 > Math.PI / 3.0) {
            theta1 = Math.PI / 3.0;
        }

// Compute the angle of the secondary segment
        double cosTheta2 = (r * r - BASE_LENGTH * BASE_LENGTH - SECONDARY_LENGTH * SECONDARY_LENGTH) / (2.0 * BASE_LENGTH * SECONDARY_LENGTH);
        double theta2 = Math.atan2(Math.sqrt(1 - cosTheta2 * cosTheta2), cosTheta2);

// Limit angle 2 to 120 degrees
        if (theta2 > 2.0 * Math.PI / 3.0) {
            theta2 = 2.0 * Math.PI / 3.0;
        } else if (theta2 < -2.0 * Math.PI / 3.0) {
            theta2 = -2.0 * Math.PI / 3.0;
        }

// Compute the sum of theta1 and theta2
        double sumAngles = theta1 + theta2;

// Compensate for the angle 2 limit by adjusting angle 1 if necessary
        if (sumAngles > Math.PI / 2.0) {
            theta1 = Math.PI / 2.0 - theta2;
        } else if (sumAngles < -Math.PI / 2.0) {
            theta1 = -Math.PI / 2.0 - theta2;
        }

// Limit angle 1 to 60 degrees again
        if (theta1 < -Math.PI / 3.0) {
            theta1 = -Math.PI / 3.0;
        } else if (theta1 > Math.PI / 3.0) {
            theta1 = Math.PI / 3.0;
        }

// Compute the joint angles in degrees
        double angle1 = Math.toDegrees(theta1);
        double angle2 = Math.toDegrees(theta2);

// Print the computed joint angles
        System.out.println("Joint angle 1: " + angle1);
        System.out.println("Joint angle 2: " + angle2);

    }
}