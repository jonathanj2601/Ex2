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
        assertEquals(false, SCell.isNumber("((8))"));
        assertEquals(false, SCell.isNumber("=8"));
        assertEquals(false, SCell.isNumber(""));

    }
    @org.junit.jupiter.api.Test
    void isForm() {
        assertEquals(true, SCell.isForm("=1+3+4"));
        assertEquals(true, SCell.isForm("=A1+2"));
        assertEquals(true, SCell.isForm("=B23+5+6"));
        assertEquals(true, SCell.isForm("=Z99*8/2"));
        assertEquals(true, SCell.isForm("=A1+b34+4+5"));
        assertEquals(true, SCell.isForm("=A1+B34"));
        assertEquals(true, SCell.isForm("=A1+b2*(C3+4)"));
        assertEquals(true, SCell.isForm("=X0+34"));
        assertEquals(false, SCell.isForm("=noMatch"));
        assertEquals(false, SCell.isForm("=123A"));
        assertEquals(false, SCell.isForm("=AB100"));
        assertEquals(false, SCell.isForm("=a45text"));
        assertEquals(false, SCell.isForm("=jsdk m"));

    }
    @org.junit.jupiter.api.Test
    void computeform(){
        assertEquals(22.5, SCell.computeForm("=(1+2)*3/(2/5)"));
        assertEquals(13.0, SCell.computeForm("=10/(5−3)+2*4"));
        assertEquals(4.5, SCell.computeForm("=(8-6)*9/(3+1)"));
        assertEquals(15.0, SCell.computeForm("=12*(3+2)/4"));
        assertEquals(10.5, SCell.computeForm("=15/(3*2)+8"));
        assertEquals(10.0, SCell.computeForm("=(6+4)*2/(8−6)"));

    }

    @org.junit.jupiter.api.Test
    void isText() {
    }
    @org.junit.jupiter.api.Test
    void findLastOperatorIndex(){
        assertEquals(7, SCell.findLastOperatorIndex("(1+2)*3/(2/5)"));
        assertEquals(9, SCell.findLastOperatorIndex("((1-1)*1)*1"));
        assertEquals(-1, SCell.findLastOperatorIndex("123"));
        assertEquals(3, SCell.findLastOperatorIndex("1*2-3/4"));
        assertEquals(13, SCell.findLastOperatorIndex("((1+2)*(3-4))+5"));
        assertEquals(5, SCell.findLastOperatorIndex("(1-2)*(3+4)"));
        assertEquals(3, SCell.findLastOperatorIndex("1+3+4"));
    }
}