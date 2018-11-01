package top.plusy.plugtest;

import net.sf.json.JSONObject;
import top.plusy.EncodeDecodePlug;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Iterator;

public class TKXXTools extends JFrame {
    private JPanel contentPane;
    private JTextArea textArea1;
    private JButton base64JsonButton;
    private JTextArea textArea2;
    private JButton base64BinaryButton;
    private JButton AES128CBCDecodeButton;
    private JButton AES128CBCEncodeButton;
    private JTextField textField1;
    private Image image;

    private Encryption encryption = new Encryption();

    private static String JsonFormat(JSONObject jsonObject)
    {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{\n");
        Iterator keys = jsonObject.keys();
        while(keys.hasNext())
        {
            Object key = keys.next();
            Object val = jsonObject.get(key);
            stringBuilder.append("    ").append(key).append(" : ").append(val).append('\n');
        }
        stringBuilder.append("}");

        return stringBuilder.toString();
    }

    private Image getResourceImage()
    {
        Image image = null;
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("ToolKey.png");
        try {
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    private TKXXTools() {
        setContentPane(contentPane);
        setTitle("TKXXTools");
        image = getResourceImage();
        textArea1.setLineWrap(true);
        textArea2.setLineWrap(true);
        textArea1.setEditable(true);
        textArea2.setEditable(false);
        textArea2.setFocusable(true);
        textField1.setText(encryption.getKey());

        base64JsonButton.addActionListener(actionEvent -> {
            String base64String = textArea1.getText().trim();
            if(base64String.length() > 0) {
                try {
                    JSONObject jsonObject = EncodeDecodePlug.decodeFromBase64(base64String);
                    textArea2.setText(JsonFormat(jsonObject));
                } catch (Exception ex) {
                    textArea2.setText("输入有误:\n");
                    textArea2.append(ex.getMessage());
                }
            }
        });

        base64BinaryButton.addActionListener(actionEvent -> {
            String base64String = textArea1.getText().trim();
            try {
                byte[] binary = Base64.getDecoder().decode(base64String);
                textArea2.setText("");
                for (int i = 0; i < binary.length/2; i++) {
                    textArea2.append(String.format("%02X%02X ", binary[2*i], binary[2*i+1]));
                    if ((i + 1) % 8 == 0) {
                        textArea2.append("\n");
                    }
                }
                if(binary.length%2 == 1) {
                    textArea2.append(String.format("%02X", binary[binary.length-1]));
                }
            }catch (Exception ex){
                textArea2.setText("不是有效BSAE64编码:\n"+ex.getMessage());
            }
        });

        AES128CBCDecodeButton.addActionListener(actionEvent -> {
            String base64String = textArea1.getText().trim();
            try {
                String key = textField1.getText();
                if(key.length() != 16)
                {
                    textArea2.setText("Invalid KEY");
                }
                else {
                    encryption.setKey(key);
                    String result = encryption.decrypt(base64String);
                    textArea2.setText(result);
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        AES128CBCEncodeButton.addActionListener(actionEvent -> {
            String base64String = textArea1.getText().trim();
            try {
                String key = textField1.getText();
                if(key.length() != 16)
                {
                    textArea2.setText("Invalid KEY");
                }
                else {
                    encryption.setKey(key);
                    String result = encryption.encrypt(base64String);
                    textArea2.setText(result);
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        TKXXTools dialog = new TKXXTools();
        dialog.pack();
        dialog.setIconImage(dialog.image);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        dialog.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
