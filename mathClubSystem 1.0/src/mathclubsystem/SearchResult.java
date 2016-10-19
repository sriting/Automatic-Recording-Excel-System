
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

//Search excel deponds on ID
public class SearchResult extends JFrame implements ActionListener,KeyListener{
    JPanel jpsubTitle,jpsubInfo,jpRegister;
    JLabel jlsubTitle,jlsubUseTip,jlsubInfoID,jlsubInfoName,jlsubInfoReg,jlsubInfoIDvalue,jlsubInfoNamevalue,jlsubInfoRegvalue;
    JCheckBox jcInfoBook1,jcInfoBook2;//复选方框
    //JRadioButton jlsubInfoRegTrue,jlsubInfoRegFalse;//单选框
    JButton jbRegister,jbNoRegister;
    String InfoID,InfoName,InfoReg;
    ExcelController ec = new ExcelController();
    int stage;
    
    public static void main(String[] args) throws IOException{
        String Id =null;
        int st = 1;
        SearchResult searchID = new SearchResult(Id,st);
    }
    
    //构造函数
    public SearchResult(String ID,int st) throws IOException{
        //创建组件
        jlsubTitle = new JLabel("社员信息：");
        jlsubUseTip = new JLabel("快捷键使用：按两下‘Tab’后回车键(Enter)，登记；两下Tab后删除键(Backspace),取消登记");
        jlsubInfoID = new JLabel("学号：");
        jlsubInfoName = new JLabel("| 姓名：");
        jlsubInfoReg = new JLabel("| 是否领过：");
        
        InfoID=ID;//装传递过来的参数
        stage=st;
        
        ExcelController ec = new ExcelController();//使用excel
        jlsubInfoIDvalue = new JLabel(ID);
        jlsubInfoNamevalue = new JLabel(ExcelController.getName(ID, stage));//！！读取excel
        jlsubInfoRegvalue = new JLabel(ExcelController.getRegister(ID, stage));
        jcInfoBook1 = new JCheckBox("线性代数",true);//true即复选框默认勾选
        jcInfoBook2 = new JCheckBox("微积分",true);
        //jcInfoBook1.getText();
        
        jpsubTitle = new JPanel();
        jpsubTitle.setPreferredSize(new Dimension(500,50));
        jpsubInfo = new JPanel();
        jpsubInfo.setPreferredSize(new Dimension(500,150));
        jpRegister = new JPanel();
        jpRegister.setPreferredSize(new Dimension(500,100));
        
        jbRegister = new JButton("登记(Enter)");
        jbNoRegister = new JButton("不登记(Backspace)");
        jbRegister.addActionListener(this);//添加监听
        jbNoRegister.addActionListener(this); 
        jbRegister.addKeyListener(this);//添加键盘监听（快捷键）
        jbNoRegister.addKeyListener(this);
        
        jpsubTitle.add(jlsubTitle);
        jpsubTitle.add(jlsubUseTip);
        
        jpsubInfo.add(jlsubInfoID);
        jpsubInfo.add(jlsubInfoIDvalue);
        jpsubInfo.add(jlsubInfoName);
        jpsubInfo.add(jlsubInfoNamevalue);
        jpsubInfo.add(jlsubInfoReg);
        jpsubInfo.add(jlsubInfoRegvalue);
        //jpsubInfo.add(jlsubInfoRegTrue);
        //jpsubInfo.add(jlsubInfoRegFalse);
        jpsubInfo.add(jcInfoBook1);
        jpsubInfo.add(jcInfoBook2);
        
        jpRegister.add(jbRegister);
        jpRegister.add(jbNoRegister);
        
        this.add(jpsubTitle,BorderLayout.NORTH);
        this.add(jpsubInfo,BorderLayout.CENTER);
        this.add(jpRegister,BorderLayout.SOUTH);
        
        //设置布局管理器
        this.setTitle("领资料登记");
        this.setSize(500,300);
        this.setLocation(800,500);//屏幕居中显示
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//Exit..父窗口也会关；用DISPOSE只关闭子窗口
        this.setVisible(true);//显示窗体
    }
    
    public void actionPerformed(ActionEvent e){ //实现ActionListener的默认方法
        String Set1="",Set2="",set="已领取";
        boolean isSet=true;
        if(e.getActionCommand()=="登记(Enter)"){
            if(jlsubInfoNamevalue.getText()=="查无此人！"){
                JOptionPane.showMessageDialog(null,"此ID号不存在，无法登记！","提示消息",JOptionPane.ERROR_MESSAGE);
            }else{
                //写入excel 
                try {
                    if(jcInfoBook1.isSelected()==true && jcInfoBook2.isSelected()==true){ 
                        isSet=ExcelController.setRegister(InfoID, stage,3);
                        if(isSet){
                            Set1=" |线代|";Set2=" |微积分|";
                        }
                    }else if(jcInfoBook1.isSelected()==true){
                        isSet=ExcelController.setRegister(InfoID, stage,1);
                        if(isSet){
                            Set1=" |线代|";
                        }
                    }else if(jcInfoBook2.isSelected()==true){ 
                        isSet=ExcelController.setRegister(InfoID, stage,2);
                        if(isSet){
                            Set2=" |微积分|";
                        }
                    }
                }catch (IOException ex) {
                    Logger.getLogger(SearchResult.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(isSet){
                    JOptionPane.showMessageDialog(null,"登记成功！"+set+Set1+Set2,"提示消息",JOptionPane.WARNING_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null,"有重复领取，无法登记！","提示消息",JOptionPane.ERROR_MESSAGE);
                }
            }
            dispose();
        }else if(e.getActionCommand()=="不登记(Backspace)"){
            dispose();
        }
    }
    
    public void keyPressed(KeyEvent e){ //实现KeyListener的默认方法
        String Set1="",Set2="",set="已领取";
        boolean isSet=true;
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(jlsubInfoNamevalue.getText()=="查无此人！"){
                JOptionPane.showMessageDialog(null,"此ID号不存在，无法登记！","提示消息",JOptionPane.ERROR_MESSAGE);
            }else{
                //写入excel 
                try {
                    if(jcInfoBook1.isSelected()==true && jcInfoBook2.isSelected()==true){ 
                        isSet=ExcelController.setRegister(InfoID, stage,3);
                        if(isSet){
                            Set1="线代";Set2=" 微积分";
                        }
                    }else if(jcInfoBook1.isSelected()==true){
                        isSet=ExcelController.setRegister(InfoID, stage,1);
                        if(isSet){
                            Set1="线代";
                        }
                    }else if(jcInfoBook2.isSelected()==true){ 
                        isSet=ExcelController.setRegister(InfoID, stage,2);
                        if(isSet){
                            Set2=" 微积分";
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(SearchResult.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(isSet){
                    JOptionPane.showMessageDialog(null,"登记成功！"+set+Set1+Set2,"提示消息",JOptionPane.WARNING_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null,"有重复领取，无法登记！","提示消息",JOptionPane.ERROR_MESSAGE);
                } 
            }
            dispose();
        }else if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
            dispose();
        }
    }
    public void keyReleased(KeyEvent e){
        
    }
    public void keyTyped(KeyEvent e){
        
    }
}
