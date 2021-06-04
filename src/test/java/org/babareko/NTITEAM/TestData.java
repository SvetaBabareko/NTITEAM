package org.babareko.NTITEAM;

import org.babareko.NTITEAM.model.Lord;
import org.babareko.NTITEAM.model.Planet;

import java.util.List;

public class TestData {
    public static final int Lord1_ID = 1;
    public static final Lord lord1 = new Lord(Lord1_ID, "Lord1", 3);
    public static final Lord lord2 = new Lord(Lord1_ID + 1, "lord2", 343);
    public static final Lord lord3 = new Lord(Lord1_ID + 2, "lord3", 324);
    public static final Lord lord4 = new Lord(Lord1_ID + 3, "lord4", 23);
    public static final Lord lord5 = new Lord(Lord1_ID + 4, "lord5", 134);
    public static final Lord lord6 = new Lord(Lord1_ID + 5, "lord6", 13);
    public static final Lord lord7 = new Lord(Lord1_ID + 6, "lord7", 3344);
    public static final Lord lord8 = new Lord(Lord1_ID + 7, "lord8", 83);
    public static final Lord lord9 = new Lord(Lord1_ID + 8, "lord9", 934);
    public static final Lord lord10 = new Lord(Lord1_ID + 9, "lord10", 93);
    public static final Lord lord11 = new Lord(Lord1_ID + 10, "lord11", 6374);
    public static final Lord lord12 = new Lord(Lord1_ID + 11, "lord12", 53);
    public static final Lord lord13 = new Lord(Lord1_ID + 12, "lord13", 6734);
    public static final Lord lord14 = new Lord(Lord1_ID + 13, "lord14", 73);
    public static final Lord lord15 = new Lord(Lord1_ID + 14, "lord15", 334);

    public static final List<Lord> lordList = List.of(lord1, lord2, lord3, lord4, lord5, lord6, lord7,
            lord8, lord9, lord10, lord11, lord12, lord13, lord14, lord15);


    public static final int planet1_ID = 16;
    public static final Planet planet1 = new Planet(planet1_ID, "Planet1", lord3);
    public static final Planet planet2 = new Planet(planet1_ID + 1, "Planet2", lord3);
    public static final Planet planet3 = new Planet(planet1_ID + 2, "Planet3", lord3);
    public static final Planet planet4 = new Planet(planet1_ID + 3, "Planet4", lord2);
    public static final Planet planet5 = new Planet(planet1_ID + 4, "Planet5", lord2);
    public static final Planet planet6 = new Planet(planet1_ID + 5, "Planet6", lord1);
    public static final Planet planet7 = new Planet(planet1_ID + 6, "Planet7", lord4);
    public static final Planet planet8 = new Planet(planet1_ID + 7, "Planet8", null);
    public static final Planet planet9 = new Planet(planet1_ID + 8, "Planet9", null);
    public static final Planet planet10 = new Planet(planet1_ID + 9, "Planet10", null);
    public static final Planet planet11 = new Planet(planet1_ID + 10, "Planet11", null);
    public static final Planet planet12 = new Planet(planet1_ID + 11, "Planet12", null);
    public static final Planet planet13 = new Planet(planet1_ID + 12, "Planet13", lord7);
    public static final Planet planet14 = new Planet(planet1_ID + 13, "Planet14", lord7);
    public static final Planet planet15 = new Planet(planet1_ID + 14, "Planet15", null);

    public static final List<Planet> planetList = List.of(planet1, planet2, planet3, planet4, planet5, planet6, planet7,
            planet8, planet9, planet10, planet11, planet12, planet13, planet14, planet15);

}
