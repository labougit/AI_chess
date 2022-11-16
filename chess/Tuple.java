package chess;

public class Tuple {
    
    public Integer a;
    public Integer b;
    public String c;

    public Tuple(Integer i_a, Integer i_b,String i_c)
    {
        a = i_a;
        b = i_b;
        c = i_c;
    }

    public void setFirst(Integer i)
    {
        a = i;
    }
    public void setSecond(Integer i)
    {
        b = i;
    }
    public void setThird(Integer i)
    {
        c = i;
    }
    public Integer getFirst()
    {
        return a;
    }
    public Integer getSecond()
    {
        return b;
    }
    public String getThird()
    {
        return c;
    }

}
