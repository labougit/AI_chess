package chess;

public class Tuple_float {
    
    public Float a;
    public Integer b;
    public String c;

    public Tuple_float(Float i_a, Integer i_b,String i_c)
    {
        a = i_a;
        b = i_b;
        c = i_c;
    }

    public void setFirst(Float i)
    {
        a = i;
    }
    public void setSecond(Integer i)
    {
        b = i;
    }
    public void setThird(String i)
    {
        c = i;
    }
    public Float getFirst()
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
