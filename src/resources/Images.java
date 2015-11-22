package resources;

import java.util.Random;

import javax.swing.ImageIcon;

public class Images {
	
	// BACKGROUNDS
	public static final String plainHomePageBackground = "img/backgrounds/QuoteMeBackground1.jpg";
	public static final String parrotHomePageBackground = "img/backgrounds/QuoteMeBackground2.jpg";
	public static final String parrotHomePageBackgroundPixellated6 = "img/backgrounds/QuoteMeBackground-Pixellated6.jpg";
	public static final String parrotHomePageBackgroundPixellated15 = "img/backgrounds/QuoteMeBackground-Pixellated15.jpg";
	public static final String parrotHomePageBackgroundPixellated19 = "img/backgrounds/QuoteMeBackground-Pixellated19.jpg";
	public static final String parrotHomePageBackgroundPixellated23 = "img/backgrounds/QuoteMeBackground-Pixellated23.jpg";
	
	// AVATARS
	public static final String parrotAvatarBluePixellated = "img/avatars/QuoteMeAvatarBluePixellated.png";
	public static final String parrotAvatarGrayPixellated = "img/avatars/QuoteMeAvatarGrayPixellated.png";
	public static final String parrotAvatarGreenPixellated = "img/avatars/QuoteMeAvatarGreenPixellated.png";
	public static final String parrotAvatarOrangePixellated = "img/avatars/QuoteMeAvatarOrangePixellated.png";
	public static final String parrotAvatarPinkPixellated = "img/avatars/QuoteMeAvatarPinkPixellated.png";
	public static final String parrotAvatarPurplePixellated = "img/avatars/QuoteMeAvatarPurplePixellated.png";
	public static final String parrotAvatarRedPixellated = "img/avatars/QuoteMeAvatarRedPixellated.png";
	public static final String parrotAvatarYellowPixellated = "img/avatars/QuoteMeAvatarYellowPixellated.png";
	
	public static String[] avatarStrings = {
			parrotAvatarBluePixellated,
			parrotAvatarGrayPixellated, 
			parrotAvatarGreenPixellated,
			parrotAvatarOrangePixellated,
			parrotAvatarPinkPixellated,
			parrotAvatarPurplePixellated,
			parrotAvatarRedPixellated,
			parrotAvatarYellowPixellated
	};
	
	public static ImageIcon getRandomAvatar() {
		int index = new Random().nextInt(avatarStrings.length);
		return new ImageIcon(avatarStrings[index]);
	}
	// BUTTONS
	public static final String greenButton = "img/buttons/greenbutton_00.png";
	public static final String greenButtonPressed = "img/buttons/greenbutton_01.png";
	public static final String greyButton = "img/buttons/greybutton_00.png";
	public static final String greyButtonPressed = "img/buttons/greybutton_01.png";
	
	// TABS
	
	// MORE?
	
}

