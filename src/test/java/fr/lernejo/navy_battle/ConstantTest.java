package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConstantTest {

    @Test
    void getSizeOfBoard() {
        Constant constant = new Constant();
        Assertions.assertEquals(10,constant.GetSizeOfBoard());
    }
}
