package info.cleidimarviana.fonttextview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.Locale;

/**
 * Text view with a custom font.
 * <p/>
 * In the XML, use something like {@code customAttrs:FontType="roboto-thin"}. The list of fonts
 * that are currently supported are defined in the enum {@link CustomFont}. Remember to also add
 * {@code xmlns:customAttrs="http://schemas.android.com/apk/res-auto"} in the header.
 */
public class CustomTextView extends TextView {

    private static final String sScheme =
            "http://schemas.android.com/apk/res-auto";
    private static final String sAttribute = "fontType";

    static enum CustomFont {
        ROBOTO_THIN("fonts/morningtype.ttf"),
        ROBOTO_BOLD("fonts/morningtype_bold.ttf"),
        ROBOTO_LIGHT("fonts/morningtype_light.ttf"),
        FREE_MONO("freefont/FreeMono.ttf"),
        FREE_MONO_BOLD("freefont/FreeMonoBold.ttf"),
        FREE_MONO_BOLD_OBLIQUE("freefont/FreeMonoBoldOblique.ttf"),
        FREE_MONO_OBLIQUE("freefont/FreeMonoOblique.ttf"),
        FREE_SANS("freefont/FreeSans.ttf"),
        FREE_SANS_BOLD("freefont/FreeSansBold.ttf"),
        FREE_SANS_BOLD_OBLIQUE("freefont/FreeSansBoldOblique.ttf"),
        FREE_SANS_OBLIQUE("freefont/FreeSansOblique.ttf"),
        FREE_SANS_SERIF("freefont/morningtype_light.ttf"),
        FREE_SERIF("freefont/FreeSerif.ttf"),
        FREE_SERIF_BOLD("freefont/FreeSerifBold.ttf"),
        FREE_SERIF_BOLD_ITALIC("freefont/FreeSerifBoldItalic.ttf"),
        FREE_SERIF_ITALIC("freefont/FreeSerifItalic.ttf");

        private final String fName;

        CustomFont(String fName) {
            this.fName = fName;
        }

        static CustomFont fromString(String fontName) {
            return CustomFont.valueOf(fontName.toUpperCase(Locale.US));
        }

        public Typeface asTypeface(Context context) {
            return Typeface.createFromAsset(context.getAssets(), fName);
        }
    }

    /**
     * This constructor takes attribute.
     * @param context       context
     * @param attrs         atribute
     */

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        if (isInEditMode()) {
            return;
        } else {
            final String fontName = attrs.getAttributeValue(sScheme, sAttribute);

            if (fontName == null) {
                throw new IllegalArgumentException("You must provide \"" + sAttribute + "\" for your text view");
            } else {
                final Typeface customTypeface = CustomFont.fromString(fontName).asTypeface(context);
                setTypeface(customTypeface);
            }
        }
    }
}