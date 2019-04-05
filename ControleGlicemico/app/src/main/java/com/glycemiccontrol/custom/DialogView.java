package com.glycemiccontrol.custom;


import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.text.method.LinkMovementMethod;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.ColorRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import com.glycemiccontrol.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import static android.view.View.VISIBLE;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static android.widget.LinearLayout.HORIZONTAL;

public class DialogView extends Dialog {

    private DialogAnimator enterAnimator;
    private DialogAnimator exitAnimator;

    private DialogView(@NonNull Context context) {
        super(context, R.style.DialogViewTheme);
    }

    public void setEnterAnimator(DialogAnimator animator) {
        enterAnimator = animator;
    }

    public void setExitAnimator(DialogAnimator animator) {
        exitAnimator = animator;
    }

    public void show(boolean animated) {
        if (animated && enterAnimator != null) {
            View view = findViewById(R.id.dialogContainer);
            view.setVisibility(View.GONE);
            enterAnimator.animate(this, view);
        } else {
            show();
        }
    }

    public void dismiss(boolean animated) {
        if (animated && exitAnimator != null) {
            View view = findViewById(R.id.dialogContainer);
            exitAnimator.animate(this, view);
        } else {
            dismiss();
        }
    }

    public interface CallbackListener {

        void viewClicked(DialogView dialog, View button);
    }

    public interface DialogAnimator {

        void animate(DialogView dialog, View view);
    }

    public static class Builder {

        String title;
        CharSequence description;
        int stackOrientation;
        List<Button> buttons = new ArrayList<>();
        LayoutInflater inflater;
        int descriptionGravity;
        WeakReference<Context> context;
        View customView;
        DialogView dialog;
        Integer titleColor;
        private int visibility = VISIBLE;

        public Builder(Context context) {
            this.context = new WeakReference<>(context);
            this.inflater = LayoutInflater.from(context);
            this.dialog = new DialogView(context);
        }

        private static void setAllParentsClip(View v, boolean enabled) {
            while (v.getParent() != null && v.getParent() instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) v.getParent();
                viewGroup.setClipChildren(enabled);
                viewGroup.setClipToPadding(enabled);
                v = viewGroup;
            }
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String title) {
            this.description = title;
            return this;
        }

        public Builder description(CharSequence title) {
            this.description = title;
            return this;
        }

        public Builder descriptionVisibility(int visibility) {
            this.visibility = visibility;
            return this;
        }

        public Builder descriptionGravity(int gravity) {
            this.descriptionGravity = gravity;
            return this;
        }

        public Builder orientation(int orientation) {
            this.stackOrientation = orientation;
            return this;
        }

        public Builder titleColor(@ColorRes int titleColor) {
            this.titleColor = titleColor;
            return this;
        }

        public Builder customView(View view) {
            customView = view;
            return this;
        }

        public Builder addButton(String title,
                                 @LayoutRes int layoutRes,
                                 CallbackListener callback) {

            Button button = (Button) inflater.inflate(layoutRes, null);
            button.setText(title);
            if (callback != null) {
                button.setOnClickListener(v -> callback.viewClicked(dialog, v));
            }
            buttons.add(button);
            return this;
        }

        public DialogView build() {

            RelativeLayout parent = (RelativeLayout) inflater.inflate(
                    R.layout.custom_dialog_view, null);

            LinearLayout dialogView = parent.findViewById(
                    R.id.dialogContainer);

            TextView title = dialogView.findViewById(R.id.dialogTitle);
            TextView description = dialogView.findViewById(R.id.dialogDescription);
            description.setGravity(descriptionGravity);
            description.setVisibility(visibility);
            title.setText(this.title);
            description.setText(this.description);
            description.setMovementMethod(LinkMovementMethod.getInstance());
            if (titleColor != null) {
                title.setTextColor(context.get().getResources().getColor(titleColor));
            }

            if (customView != null) {
                dialogView.addView(customView);
            }

            if (buttons.size() > 0) {
                LinearLayout buttonsStack;

                if (stackOrientation == HORIZONTAL) {
                    LinearLayout.LayoutParams stackParams =
                            new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
                    stackParams.gravity = Gravity.CENTER;
                    stackParams.topMargin = getPixelValue(dialogView.getContext(), 35);
                    buttonsStack = new LinearLayout(dialogView.getContext());
                    buttonsStack.setLayoutParams(stackParams);
                } else {
                    LinearLayout.LayoutParams stackParams =
                            new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
                    stackParams.topMargin = getPixelValue(dialogView.getContext(), 35);
                    buttonsStack = new LinearLayout(dialogView.getContext());
                    buttonsStack.setLayoutParams(stackParams);
                }

                buttonsStack.setOrientation(stackOrientation);

                Button lastButton = buttons.remove(buttons.size() - 1);

                for (Button button : buttons) {

                    View spacingView = new View(context.get());
                    int spacingDimen = getPixelValue(context.get(), 10);

                    if (stackOrientation == HORIZONTAL) {
                        spacingView.setLayoutParams(new ViewGroup.LayoutParams(spacingDimen, 0));
                    } else {
                        spacingView.setLayoutParams(new ViewGroup.LayoutParams(0, spacingDimen));
                    }

                    buttonsStack.addView(button);
                    buttonsStack.addView(spacingView);
                }

                buttonsStack.addView(lastButton);
                dialogView.addView(buttonsStack);
            }

            dialog.setContentView(parent);
            setAllParentsClip(dialogView, false);
            return dialog;
        }

        private int getPixelValue(Context context, int dimenId) {

            Resources resources = context.getResources();

            return (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    dimenId,
                    resources.getDisplayMetrics()
            );
        }
    }

}

