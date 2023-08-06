package com.example.wm.tools;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FontUtility {

    public static void applyFontStyle(Context context, View view, String fontStyle) {
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            Typeface typeface = getTypeface(context, fontStyle);
            textView.setTypeface(typeface);
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                applyFontStyle(context, viewGroup.getChildAt(i), fontStyle);
            }
        }
    }

    public static void applyFontSize(View view, int fontSize) {
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            textView.setTextSize(fontSize);
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                applyFontSize(viewGroup.getChildAt(i), fontSize);
            }
        }
    }

    private static Typeface getTypeface(Context context, String fontStyle) {
        Typeface typeface;
        switch (fontStyle) {
            case "regular":
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/regular.ttf");
                break;
            case "bold":
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/bold.ttf");
                break;
            case "italic":
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/italic.ttf");
                break;
            default:
                typeface = Typeface.DEFAULT; // Default typeface
                break;
        }
        return typeface;
    }
}
