package softuni.artgallery.services.services.validations;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class EscapeHtmlServiceImpl implements EscapeHtmlService {
    private HashMap<Integer, String> data;

    public EscapeHtmlServiceImpl() {
       setData();

    }

    public HashMap<Integer, String> getData() {
        return data;
    }

    public void setData(HashMap<Integer, String> data) {
        this.data = data;
    }

    private void setData() {
        this.data = new HashMap<>();
        this.data.put(34, "&quot;");  // "
        this.data.put(35, "&#35;");   // #
        this.data.put(38, "&amp;");   // &
        this.data.put(39, "&apos;");  // '
        this.data.put(60, "&lt;");    // <
        this.data.put(62, "&gt;");    // >
    }

    @Override
    public String escapeHtml(String str) {

            StringBuilder builder = new StringBuilder();
            char[] charArray = str.toCharArray();

            for (char c: charArray) {
                String  symbol= data.get((int)c);
                if (symbol == null) {
                    if (c > 0x7F)
                        builder.append("&#")
                                .append(Integer.toString(c, 10))
                                .append(";");
                    else
                        builder.append(c);
                }
                else
                    builder.append(symbol);
            }
            return builder.toString();
        }



    }

