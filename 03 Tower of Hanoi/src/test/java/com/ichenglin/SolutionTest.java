package com.ichenglin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Nested
    @DisplayName("Question #1: Check Disk Movable")
    class Question_01 {

        @Test
        @DisplayName("Valid Test Cases with Movable Disks")
        public void valid_movable() {
            HanoiTower board = new HanoiTower(new byte[][]{{5, 4, 3, 2, 1}, {}, {}}, 0);
            assertTrue(Solution.disk_movable(board, 0, 1));
            assertFalse(Solution.disk_movable(board, 1, 0));
        }

    }

}