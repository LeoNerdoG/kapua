package org.eclipse.persistence.logging.slf4j;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.eclipse.persistence.logging.SessionLogEntry;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class SLF4JLoggerTest extends Assert {

    SLF4JLogger logger;

    @Before
    public void start() {
        logger = new SLF4JLogger();
    }

    @Test
    public void setPermittedLevelTest() {
        int[] testLevels = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        for (int level : testLevels) {
            logger.setLevel(level);
            assertEquals(level, logger.getLevel());
        }
    }

    @Test
    public void setNonPermittedLevelTest() {
        int[] testLevels = {9, -1, 1000, 22};
        for (int level : testLevels) {
            try {
                logger.setLevel(level);
                fail();
            } catch (IllegalArgumentException e) {
                // test passed
            }
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void setLevelWithUnknownCategoryTest() {
        logger.setLevel(1, "unknown");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getLevelWithUnknownCategoryTest() {
        logger.getLevel("unknown");
    }

    @Test
    public void getLevelWithKnownCategoryTest() {
        assertEquals(5, logger.getLevel("CONNECTION"));
    }

    @Test
    public void shouldLogTrueTest() {
        logger.setLevel(2);
        assertTrue(logger.shouldLog(5));
        assertTrue(logger.shouldLog(7));
        assertTrue(logger.shouldLog(100));
        assertTrue(logger.shouldLog(2));
    }

    @Test
    public void shouldLogFalseTest() {
        logger.setLevel(6);
        assertFalse(logger.shouldLog(2));
        assertFalse(logger.shouldLog(5));
        assertFalse(logger.shouldLog(-12));
    }

    @Test
    public void shouldLogWithCategoryTrueTest() {
        logger.setLevel(2);
        assertTrue(logger.shouldLog(5, "SERVER"));
        assertTrue(logger.shouldLog(5, "SQL"));
        assertTrue(logger.shouldLog(5, "All"));
        assertTrue(logger.shouldLog(2, "All"));
    }

    @Test
    public void shouldLogWithCategoryFalseTest() {
        logger.setLevel(8);
        assertFalse(logger.shouldLog(5, "All"));
        assertFalse(logger.shouldLog(7, "All"));
        assertFalse(logger.shouldLog(-1, "All"));
        assertFalse(logger.shouldLog(2, "All"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldLogWithUnknownCategoryTest() {
        logger.shouldLog(1, "unknown");
    }

    @Test
    public void logWithLogEntryNonLoggableTest() {
        SessionLogEntry logEntry = new SessionLogEntry(null, 2, null, new Throwable());
        logger.log(logEntry);
    }

    @Test
    public void logWithLogEntryNullTest() {
        logger.log(null);
    }

    @Test
    public void logWithLogEntryDifferentLevelsTest() {
        for (int i = 0; i < 9; i++) {
            try {
                logger.setLevel(i);
                logger.log(new SessionLogEntry(null, i, "All", new Exception()));
            } catch (Exception e) {
                fail("No exception should be thrown");
            }
        }
    }

    @Test
    public void logWithLogEntryLoggableWithoutExceptionDebugTest() {
        SessionLogEntry logEntry = new SessionLogEntry(null, "2", null);
        logger.setLevel(2);
        logger.log(logEntry);
    }

    @Test(expected = IllegalArgumentException.class)
    public void logWithLogEntryUnknownCategoryTest() {
        SessionLogEntry logEntry = new SessionLogEntry(null, 2, "unknown", new Throwable());
        logger.log(logEntry);
    }
}
