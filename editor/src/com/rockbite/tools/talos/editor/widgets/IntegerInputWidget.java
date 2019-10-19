package com.rockbite.tools.talos.editor.widgets;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class IntegerInputWidget extends Table {


    private ChangeListener listener;

    private TextField regularCarrier;
    private Label regularLabel;
    private int value;

    public IntegerInputWidget(String text, Skin skin) {
        setSkin(skin);

        Table table = new Table();

        regularLabel = new Label(text, getSkin());
        regularCarrier = new TextField("", getSkin());
        regularCarrier.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());

        table.add(regularLabel).left().row();
        table.add(regularCarrier).left().padTop(5).width(68);

        regularCarrier.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                getValue();
                if(listener != null) {
                    listener.changed(event, actor);
                }
            }
        });

        regularCarrier.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

                if(regularCarrier.getSelection().length() == 0) {
                    regularCarrier.selectAll();
                }
            }
        });

        add(table).padLeft(4);
    }

    public void setListener(ChangeListener listener) {
        this.listener = listener;
    }

    public int getValue() {
        int val = 0;
        try {
            val = Integer.parseInt(regularCarrier.getText());
        } catch (NumberFormatException e) {
            val = 0;
        }
        return val;
    }

    public void setValue(int val) {
        value = val;
        regularCarrier.setText(Integer.toString(val));
    }

    public void setText(String text) {
        regularLabel.setText(text);
    }
}