package gui;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Created by Dima on 10.05.2016.
 */
class HintTextField extends JTextField implements FocusListener {

    private String hint;

    private boolean showingHint;

    public HintTextField(final String hint) {
        super (hint);
        this.hint = hint;
        this.showingHint = true;
        super.addFocusListener (this);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (this.getText ().isEmpty ()) {
            super.setText ("");
            showingHint = false;
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (this.getText ().isEmpty ()) {
            super.setText (hint);
            showingHint = true;
        }
    }

    public void setFocus(){
        showingHint = false;
    }
    @Override
    public String getText() {
        return showingHint ? "" : super.getText ();
    }


    @Override
    public void setText(String t) {
        super.setText(t);
        this.hint = t;
        this.showingHint = true;
    }

}