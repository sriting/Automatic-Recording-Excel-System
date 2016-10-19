
package mathclubsystem;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

//Search excel deponds on ID
public class Search extends JFrame implements ActionListener,KeyListener{
    JPanel jpTitle,jpUseTip,jpInfo,jpReturn;
    JLabel jlTitle,jlInfoID,jlInfoStage,jlUseTip1,jlUseTip2,jlUseTip3;
    JButton jbSearch,jbReturn;
    JTextField jtfEnterID;
    String infoId=null;
    int stage;
    
    public static void main(String[] args){
        int stage=1;
        Search searchID = new Search(stage);
    }
    
    //构造函数
    public Search(int s){
        //创建组件
        jlTitle = new JLabel("输入社员学号（ID）进行查询");
        jlInfoID = new JLabel("学号：");
        jlUseTip1 = new JLabel("快捷键使用：1.输入学号后，按‘Tab’键后回车（Enter），可直接查询");
        jlUseTip2 = new JLabel("           2.若想回到主页，按‘Tab’键后删除键 (BackSapce)");
        jlUseTip3 = new JLabel("注意事项：1.此页面可重复查询ID，无须每次返回首页（如需要改资料时间再返回选择,默认上学期期中）");
        
        stage = s;//装传递过来的参数
        if(stage==1){
            jlInfoStage = new JLabel("  时间： 上学期期中");
        }else if(stage==2){
            jlInfoStage = new JLabel(  "时间： 上学期期末");
        }else if(stage==3){
            jlInfoStage = new JLabel("  时间： 下学期期中");
        }else if(stage==4){
            jlInfoStage = new JLabel("  时间： 下学期期末");
        }
        
        jpTitle = new JPanel();
        //jpTitle = new JPanel(new GridLayout(3,1,0,5));//垂直安排三个label;3为行数，1为列数,0为水平间距，5为垂直间距
        jpTitle.setPreferredSize(new Dimension(700,20));
        jpUseTip = new JPanel();
        jpUseTip.setPreferredSize(new Dimension(700,70));
        jpInfo = new JPanel();
        jpInfo.setPreferredSize(new Dimension(700,200));
        jpReturn = new JPanel();
        jpReturn.setPreferredSize(new Dimension(700,100));
        
        jbSearch = new JButton("查询(Enter)");
        jbReturn = new JButton("返回主页(Backspace)");
        jbSearch.addActionListener(this);//添加监听
        jbReturn.addActionListener(this);
        jbSearch.addKeyListener(this);//添加键盘监听（快捷键）
        jbReturn.addKeyListener(this);
        
        jtfEnterID = new JTextField(10);
        
        jpTitle.add(jlTitle);
        jpUseTip.add(jlUseTip1);
        jpUseTip.add(jlUseTip2);
        jpUseTip.add(jlUseTip3);
        
        jpInfo.add(jlInfoID);
        jpInfo.add(jtfEnterID);
        jpInfo.add(jbSearch);
        jpInfo.add(jlInfoStage);
        
        jpReturn.add(jbReturn);
        
        this.setLayout(new GridLayout(4,1));
        this.add(jpTitle);
        this.add(jpUseTip);
        this.add(jpInfo);
        this.add(jpReturn);
        
        //设置布局管理器
        this.setTitle("领资料登记");
        this.setSize(700,400);
        this.setLocationRelativeTo(null);//屏幕居中显示
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//退出关闭JFrame,且保证JVM也推出
        this.setVisible(true);//显示窗体
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand()=="查询(Enter)"){
            infoId = jtfEnterID.getText();
            try {
                SearchResult result = new SearchResult(infoId,stage);//创建一个新界面，SearchRegister的界面
                jtfEnterID.setText("");//清空输入框
                //System.out.println("ID:"+infoId);
            } catch (IOException ex) {
                Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(e.getActionCommand()=="返回主页(Backspace)"){
            dispose();
            MathClubSystem backMain = new MathClubSystem();
        }
    }
    public void keyPressed(KeyEvent e){ //实现KeyListener的默认方法
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            infoId = jtfEnterID.getText();
            try {
                SearchResult result = new SearchResult(infoId,stage);//创建一个新界面，SearchRegister的界面       
                jtfEnterID.setText("");//清空输入框
            } catch (IOException ex) {
                Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
            dispose();
            MathClubSystem backMain = new MathClubSystem();
        }
    }
    public void keyReleased(KeyEvent e){
        
    }
    public void keyTyped(KeyEvent e){
        
    }
}
