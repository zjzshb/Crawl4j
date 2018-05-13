import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestArryList {

    @Test
    public void test4(){
        String content = "有没有这样一幅画，画里的角色让你产生难以自拔的迷恋？";
        String substring = content.substring(0,content.length() - 1)+".txt";
        System.out.println(substring);
        String content2 = "D:/data/zhihu/author_mes/00/0003c044a13adc078b62503a586e88e8/v2-79cb795ec05beeb19933be02c41cd9bb_xl.jpg";
        String substring2 = "/static"+content2.substring(7);
        System.out.println(substring2);
    }


    @Test
    public void test5(){
        List<Node> nodes = new ArrayList<Node>();
        nodes.add(new Node("11"));
        nodes.add(new Node("22"));
        nodes.add(new Node("33"));

        for(int i=0; i<nodes.size(); i++){
            Node node = nodes.get(i);
            node.val="1="+i;
            System.out.println(nodes.get(i).val);
        }
    }



}

class Node{
    String val;
    Node(String val){
        this.val = val;
    }
}
