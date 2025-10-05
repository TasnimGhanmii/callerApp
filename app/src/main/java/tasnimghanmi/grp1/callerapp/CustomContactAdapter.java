// File: CustomContactAdapter.java
package tasnimghanmi.grp1.callerapp;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomContactAdapter extends ArrayAdapter<Contact> {

    private int[] colors = {
            0xFFE91E63, // Pink
            0xFF9C27B0, // Purple
            0xFF673AB7, // Deep Purple
            0xFF3F51B5, // Indigo
            0xFF2196F3, // Blue
            0xFF03A9F4, // Light Blue
            0xFF00BCD4, // Cyan
            0xFF009688, // Teal
            0xFF4CAF50, // Green
            0xFF8BC34A, // Light Green
            0xFFCDDC39, // Lime
            0xFFFFEB3B, // Yellow
            0xFFFFC107, // Amber
            0xFFFF9800, // Orange
            0xFFF44336, // Red
            0xFF795548, // Brown
            0xFF9E9E9E, // Gray
            0xFF607D8B  // Blue Gray
    };

    public CustomContactAdapter(@NonNull Context context, @NonNull List<Contact> contacts) {
        super(context, 0, contacts);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Contact contact = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_contact, parent, false);
        }

        TextView textLetter = convertView.findViewById(R.id.text_letter);
        TextView textName = convertView.findViewById(R.id.text_name);
        TextView textPseudo = convertView.findViewById(R.id.text_pseudo);
        TextView textNum = convertView.findViewById(R.id.text_num);

        // Set contact data
        if (contact != null) {
            // Name
            if (contact.nom != null && !contact.nom.isEmpty()) {
                String name = contact.nom;
                textName.setText(name);
                char firstLetter = Character.toUpperCase(name.charAt(0));
                textLetter.setText(String.valueOf(firstLetter));

                // ✅ Correct way: change drawable color, keep shape
                GradientDrawable drawable = (GradientDrawable) textLetter.getBackground();
                if (drawable != null) {
                    drawable.setColor(getColorForLetter(firstLetter));
                }
            } else {
                textName.setText("Unknown");
                textLetter.setText("?");
                GradientDrawable drawable = (GradientDrawable) textLetter.getBackground();
                if (drawable != null) {
                    drawable.setColor(0xFF9E9E9E); // Gray
                }
            }

            // Pseudo
            textPseudo.setText("@" + (contact.pseudo != null ? contact.pseudo : "no_pseudo"));

            // Number
            textNum.setText(contact.num != null ? contact.num : "");
        }

        return convertView;
    }

    private int getColorForLetter(char letter) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int index = alphabet.indexOf(Character.toUpperCase(letter));
        if (index == -1) index = 26;
        return colors[index % colors.length];
    }
}