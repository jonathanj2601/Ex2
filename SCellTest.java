import static org.junit.jupiter.api.Assertions.*;
class SCellTest {

    @org.junit.jupiter.api.Test
    void getOrder() {
    }

    @org.junit.jupiter.api.Test
    void testToString() {
    }

    @org.junit.jupiter.api.Test
    void setData() {
    }

    @org.junit.jupiter.api.Test
    void getData() {
    }

    @org.junit.jupiter.api.Test
    void getType() {
    }

    @org.junit.jupiter.api.Test
    void setType() {
    }

    @org.junit.jupiter.api.Test
    void setOrder() {
    }

    @org.junit.jupiter.api.Test
    void isNumber() {
        assertEquals(false, SCell.isNumber("23aajh"));
        assertEquals(false, SCell.isNumber("lxsdg"));
        assertFalse(SCell.isNumber("dfg"));
        assertFalse(SCell.isNumber("df123g"));
        assertTrue(SCell.isNumber("2353"));
        assertTrue(SCell.isNumber("23"));
    }
    @org.junit.jupiter.api.Test
    void isForm() {
    }

    @org.junit.jupiter.api.Test
    void isText() {
    }
}