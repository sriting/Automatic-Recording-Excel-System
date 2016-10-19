
package mathclubsystem;

/**
 *
 * @author 蔡佳人
 */
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class MathClubSystem extends JFrame implements ActionListener{
    //定义组件
    JPanel jpMainPageTitle,jpMainPageButton,jpMainPageImage;//面板
    JLabel jlMainPageTitle,jlMainPageImage;//标签
    JButton jbMainSearchID,jbMainMerge,jbMainAnalysis;//按钮
    JRadioButton jrbS1Mid,jrbS1Final,jrbS2Mid,jrbS2Final;//单选框
    int stage;
    //JButton jbMergeChoooseFile,jbMergeGetFile,jbMergeReturn,jbDataResult,jbDataReturn;
    //JTextField jtMain,jtSearch,jtMerge,jtDataAnalysis;//文本框（输入）
    
    public static void main(String[] args) {
        MathClubSystem register=new MathClubSystem();
    }
    
    //构造函数
    public MathClubSystem(){
        //获取首页图片
        
        ImageIcon image=new ImageIcon(this.getClass().getResource("/mathclub.png"));
        
        //创建面板
        jpMainPageTitle = new JPanel();
        jpMainPageTitle.setPreferredSize(new Dimension(700,50));//设置JPanel的大小
        jpMainPageImage = new JPanel();
        jpMainPageImage.setPreferredSize(new Dimension(700,200));
        jpMainPageButton = new JPanel();
        jpMainPageButton.setPreferredSize(new Dimension(700,100));
        //创建标签
        jlMainPageTitle = new JLabel("欢迎使用西浦数学社社员领资料登记系统");
        jlMainPageImage = new JLabel(image);
        //创建按钮
        jbMainSearchID = new JButton("领资料登记");;
        jbMainMerge = new JButton("合并表格");
        jbMainAnalysis = new JButton("数据分析");  
        //设置按钮的监听,三个监听都放在actionPerformed（）中
        jbMainSearchID.addActionListener(this);//因为MathClubSystem本身实现了ActionListener，所以不用再另外实现
        jbMainMerge.addActionListener(this);
        jbMainAnalysis.addActionListener(this);
        //设置单选按钮
        jrbS1Mid = new JRadioButton("上学期期中",true);//单选框
        jrbS1Final = new JRadioButton("上学期期末");
        jrbS2Mid= new JRadioButton("下学期期中");
        jrbS2Final= new JRadioButton("下学期期末");
        ButtonGroup group = new ButtonGroup();//单选四选一：ButtonGroup不需要加进JPanel，只起到管理作用
        group.add(jrbS1Mid);
        group.add(jrbS1Final);
        group.add(jrbS2Mid);
        group.add(jrbS2Final);
        
        //加入各个组件到三个面板
        jpMainPageTitle.add(jlMainPageTitle);        
        
        jpMainPageImage.add(jlMainPageImage);
        
        jpMainPageButton.add(jrbS1Mid);
        jpMainPageButton.add(jrbS1Final);
        jpMainPageButton.add(jrbS2Mid);
        jpMainPageButton.add(jrbS2Final);
        jpMainPageButton.add(jbMainSearchID);
        jpMainPageButton.add(jbMainMerge);
        jpMainPageButton.add(jbMainAnalysis);
        
        //面板加入到JFrame
        this.add(jpMainPageTitle,BorderLayout.NORTH);//this 因为MathClubSystem本身是个JFrame
        this.add(jpMainPageImage,BorderLayout.CENTER);
        this.add(jpMainPageButton,BorderLayout.SOUTH);
        
        //设置窗体
        this.setTitle("西浦数学社社员领资料登记系统");
        this.setSize(700,400);
        this.setLocationRelativeTo(null);//屏幕居中显示
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//退出关闭JFrame,且保证JVM也推出
        this.setVisible(true);//显示窗体
        
        //锁定窗体
        this.setResizable(false);
         
    }
    
    public void actionPerformed(ActionEvent e){
        if(jrbS1Mid.isSelected()){
            stage=1;
        }else if(jrbS1Final.isSelected()){
            stage=2;
        }else if(jrbS2Mid.isSelected()){
            stage=3;
        }else if(jrbS2Final.isSelected()){
            stage=4;
        }
        if(e.getActionCommand()=="领资料登记"){
            dispose();//关闭当前页面
            Search ui = new Search(stage);//创建一个新界面，SearchRegister的界面       
        }else if(e.getActionCommand()=="合并表格"){
            dispose();
            MergeExcel merge = new MergeExcel();
        }else if(e.getActionCommand()=="数据分析"){
            dispose();
            try {
                DataAnalysis data = new DataAnalysis(stage);
            } catch (IOException ex) {
                Logger.getLogger(MathClubSystem.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 
    
}


