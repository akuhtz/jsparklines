package no.uib.jsparklines.extra;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 * Table cell renderer using different HTML tag colors for selected and not
 * selected rows. For example the default blue color can be used for not
 * selected rows, while a white color can be used for the selected rows.
 * Supported input: String objects. Other object types are rendered using the
 * DefaultTableCellRenderer.
 * <p>
 * Note that the renderer assumes that the HTML tags include the font color tag
 * setting the color to one of the two colors provided in the constructor.
 *
 * @author Harald Barsnes
 */
public class HtmlLinksRenderer implements TableCellRenderer {

    /**
     * The color to use for the HTML tags for the selected rows, in HTML color
     * code, i.e., #FFFFFF for white.
     */
    private String selectedRowFontColor;
    /**
     * The color to use for the HTML tags for the rows that are not selected, in
     * HTML color code, i.e., #000000 for black.
     */
    private String notSelectedRowFontColor;

    /**
     * Default constructor.
     *
     * @param selectedRowFontColor the color to use for the HTML tags for the
     * selected rows, in HTML color code, i.e., #FFFFFF for white.
     * @param notSelectedRowFontColor the color to use for the HTML tags for the
     * rows that are not selected, in HTML color code, i.e., #000000 for black.
     */
    public HtmlLinksRenderer(String selectedRowFontColor, String notSelectedRowFontColor) {
        this.selectedRowFontColor = selectedRowFontColor;
        this.notSelectedRowFontColor = notSelectedRowFontColor;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        JLabel label = (JLabel) new DefaultTableCellRenderer().getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);

        Color bg = label.getBackground();

        // We have to create a new color object because Nimbus returns
        // a color of type DerivedColor, which behaves strange, not sure why.
        label.setBackground(new Color(bg.getRed(), bg.getGreen(), bg.getBlue()));

        if (value != null && value instanceof String) {

            String link = (String) value;

            // update the link color depending on if the row is selected or not
            if (isSelected) {
                link = link.replace(notSelectedRowFontColor, selectedRowFontColor);
            } else {
                link = link.replace(selectedRowFontColor, notSelectedRowFontColor);
            }

            label.setText(link);
        }

        return label;
    }
}
