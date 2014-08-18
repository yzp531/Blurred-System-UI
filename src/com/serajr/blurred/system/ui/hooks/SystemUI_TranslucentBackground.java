package com.serajr.blurred.system.ui.hooks;

import com.serajr.blurred.system.ui.R;
import com.serajr.blurred.system.ui.Xposed;
import com.serajr.blurred.system.ui.fragments.BlurSettings_Fragment;

import android.content.res.XModuleResources;
import android.content.res.XResources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.XposedHelpers.ClassNotFoundError;

public class SystemUI_TranslucentBackground {
	
	public static void hook(boolean handleLoadPackage, boolean handleInitPackageResources) {
		
		try {
		
			final XModuleResources modRes = Xposed.getXposedModuleResources();
			XSharedPreferences prefs = Xposed.getXposedXSharedPreferences();
			
			// handleLoadPackage
			if (handleLoadPackage) {
				
				// quick settings
				if (prefs.getBoolean(BlurSettings_Fragment.TRANSLUCENT_QUICK_SETTINGS_PREFERENCE_KEY, BlurSettings_Fragment.TRANSLUCENT_QUICK_SETTINGS_PREFERENCE_DEFAULT)) {
				
					try {
						
						// constructor (htc)
						Class<?> QuickSettingsTileView = XposedHelpers.findClass("com.android.systemui.statusbar.phone.QuickSettingsTileView", Xposed.getXposedClassLoader());
						XposedBridge.hookAllConstructors(QuickSettingsTileView, new XC_MethodHook() {
							
							@Override
				            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
								
								// seta o background
								View view = (View) param.thisObject;
								view.setBackground(modRes.getDrawable(R.drawable.qs_tile_background));
								
							}
						});
						
					} catch (ClassNotFoundError e) {
					
						e.printStackTrace();
						
					}
				}
				
			// handleInitPackageResources
			} else if (handleInitPackageResources) {
				
				String pkg = Xposed.SYSTEM_UI_PACKAGE_NAME;
				XResources res = Xposed.getXposedInitPackageResourcesParam().res;
				int resId = 0;
				
				// header
				if (prefs.getBoolean(BlurSettings_Fragment.TRANSLUCENT_HEADER_PREFERENCE_KEY, BlurSettings_Fragment.TRANSLUCENT_HEADER_PREFERENCE_DEFAULT)) {
					
					// notification_header_bg
					resId = res.getIdentifier("notification_header_bg", "drawable", pkg);
					if (resId != 0) {
					
						// troca
						res.setReplacement(resId, new XResources.DrawableLoader() {
							
						    @Override
						    public Drawable newDrawable(XResources res, int id) throws Throwable {
						    	
						        return new ColorDrawable(Color.TRANSPARENT);
						        
						    }
						});
					}
					
					// somc_notification_header_bg (xperia)
					resId = res.getIdentifier("somc_notification_header_bg", "drawable", pkg);
					if (resId != 0) {
					
						// troca
						res.setReplacement(resId, new XResources.DrawableLoader() {
							
						    @Override
						    public Drawable newDrawable(XResources res, int id) throws Throwable {
						    	
						        return new ColorDrawable(Color.TRANSPARENT);
						        
						    }
						});
					}
					
					// ic_notify_button_bg (nexus)
					resId = res.getIdentifier("ic_notify_button_bg", "drawable", pkg);
					if (resId != 0) {
					
						// troca
						res.setReplacement(resId, new XResources.DrawableLoader() {
							
						    @Override
						    public Drawable newDrawable(XResources res, int id) throws Throwable {
						    	
						        return modRes.getDrawable(R.drawable.ic_notify_button_bg);
						        
						    }
						});
					}
					
					// notification_title_background (samsung)
					resId = res.getIdentifier("notification_title_background", "drawable", pkg);
					if (resId != 0) {
					
						// troca
						res.setReplacement(resId, new XResources.DrawableLoader() {
							
						    @Override
						    public Drawable newDrawable(XResources res, int id) throws Throwable {
						    	
						    	return new ColorDrawable(Color.TRANSPARENT);
						        
						    }
						});
					}
					
					// indi_noti_settings_bg (lg)
					resId = res.getIdentifier("indi_noti_settings_bg", "drawable", pkg);
					if (resId != 0) {
					
						// troca
						res.setReplacement(resId, new XResources.DrawableLoader() {
							
						    @Override
						    public Drawable newDrawable(XResources res, int id) throws Throwable {
						    	
						    	return new ColorDrawable(Color.TRANSPARENT);
						        
						    }
						});
					}
				}
				
				// quick settings
				if (prefs.getBoolean(BlurSettings_Fragment.TRANSLUCENT_QUICK_SETTINGS_PREFERENCE_KEY, BlurSettings_Fragment.TRANSLUCENT_QUICK_SETTINGS_PREFERENCE_DEFAULT)) {
				
					// statusbar_tools_button_frame (xperia 4.1.2 e 4.2.2)
					resId = res.getIdentifier("statusbar_tools_button_frame", "drawable", pkg);
					if (resId != 0) {
					
						// troca
						res.setReplacement(resId, new XResources.DrawableLoader() {
							
						    @Override
						    public Drawable newDrawable(XResources res, int id) throws Throwable {
						    	
						        return new ColorDrawable(Color.TRANSPARENT);
						        
						    }
						});
					}
					
					// statusbar_tools_button_frame_top (xperia 4.3)
					resId = res.getIdentifier("statusbar_tools_button_frame_top", "drawable", pkg);
					if (resId != 0) {
					
						// troca
						res.setReplacement(resId, new XResources.DrawableLoader() {
							
						    @Override
						    public Drawable newDrawable(XResources res, int id) throws Throwable {
						    	
						        return modRes.getDrawable(R.drawable.statusbar_tools_button_frame_top);
						        
						    }
						});
					}
					
					// statusbar_tools_button_frame_bottom (xperia 4.3)
					resId = res.getIdentifier("statusbar_tools_button_frame_bottom", "drawable", pkg);
					if (resId != 0) {
					
						// troca
						res.setReplacement(resId, new XResources.DrawableLoader() {
							
						    @Override
						    public Drawable newDrawable(XResources res, int id) throws Throwable {
						    	
						        return modRes.getDrawable(R.drawable.statusbar_tools_button_frame_bottom);
						        
						    }
						});
					}
					
					// qs_tile_background (cm)
					resId = res.getIdentifier("qs_tile_background", "drawable", pkg);
					if (resId != 0) {
					
						// troca
						res.setReplacement(resId, new XResources.DrawableLoader() {
							
						    @Override
						    public Drawable newDrawable(XResources res, int id) throws Throwable {
						    	
						        return modRes.getDrawable(R.drawable.qs_tile_background);
						        
						    }
						});
					}
					
					// quick_settings_minor_container_background (htc)
					resId = res.getIdentifier("quick_settings_minor_container_background", "drawable", pkg);
					if (resId != 0) {
					
						// troca
						res.setReplacement(resId, new XResources.DrawableLoader() {
							
						    @Override
						    public Drawable newDrawable(XResources res, int id) throws Throwable {
						    	
						        return new ColorDrawable(Color.TRANSPARENT);
						        
						    }
						});
					}
					
					// quick_settings_tile_background (htc)
					resId = res.getIdentifier("quick_settings_tile_background", "drawable", pkg);
					if (resId != 0) {
					
						// troca
						res.setReplacement(resId, new XResources.DrawableLoader() {
							
						    @Override
						    public Drawable newDrawable(XResources res, int id) throws Throwable {
						    	
						        return new ColorDrawable(Color.TRANSPARENT);
						        
						    }
						});
					}
					
					// quick_settings_item_background (htc)
					resId = res.getIdentifier("quick_settings_item_background", "drawable", pkg);
					if (resId != 0) {
					
						// troca
						res.setReplacement(resId, new XResources.DrawableLoader() {
							
						    @Override
						    public Drawable newDrawable(XResources res, int id) throws Throwable {
						    	
						        return new ColorDrawable(Color.TRANSPARENT);
						        
						    }
						});
					}
					
					// black_background (lg)
					resId = res.getIdentifier("black_background", "drawable", pkg);
					if (resId != 0) {
					
						// troca
						res.setReplacement(resId, new XResources.DrawableLoader() {
							
						    @Override
						    public Drawable newDrawable(XResources res, int id) throws Throwable {
						    	
						        return new ColorDrawable(Color.TRANSPARENT);
						        
						    }
						});
					}
					
					// indi_noti_qsilde_bg (lg)
					resId = res.getIdentifier("indi_noti_qsilde_bg", "drawable", pkg);
					if (resId != 0) {
					
						// troca
						res.setReplacement(resId, new XResources.DrawableLoader() {
							
						    @Override
						    public Drawable newDrawable(XResources res, int id) throws Throwable {
						    	
						        return new ColorDrawable(Color.TRANSPARENT);
						        
						    }
						});
					}
					
					// indi_noti_sim_btn (lg)
					resId = res.getIdentifier("indi_noti_sim_btn", "drawable", pkg);
					if (resId != 0) {
					
						// troca
						res.setReplacement(resId, new XResources.DrawableLoader() {
							
						    @Override
						    public Drawable newDrawable(XResources res, int id) throws Throwable {
						    	
						        return modRes.getDrawable(R.drawable.indi_noti_sim_btn);
						        
						    }
						});
					}
					
					// ir_control_bg (lg)
					resId = res.getIdentifier("ir_control_bg", "drawable", pkg);
					if (resId != 0) {
					
						// troca
						res.setReplacement(resId, new XResources.DrawableLoader() {
							
						    @Override
						    public Drawable newDrawable(XResources res, int id) throws Throwable {
						    	
						        return new ColorDrawable(Color.TRANSPARENT);
						        
						    }
						});
					}
					
					// ir_control_noti_tab_bg (lg)
					resId = res.getIdentifier("ir_control_noti_tab_bg", "drawable", pkg);
					if (resId != 0) {
					
						// troca
						res.setReplacement(resId, new XResources.DrawableLoader() {
							
						    @Override
						    public Drawable newDrawable(XResources res, int id) throws Throwable {
						    	
						        return modRes.getDrawable(R.drawable.ir_control_noti_tab_bg);
						        
						    }
						});
					}
					
					// indi_noti_brightness_panel_bg (lg)
					resId = res.getIdentifier("indi_noti_brightness_panel_bg", "drawable", pkg);
					if (resId != 0) {
					
						// troca
						res.setReplacement(resId, new XResources.DrawableLoader() {
							
						    @Override
						    public Drawable newDrawable(XResources res, int id) throws Throwable {
						    	
						        return modRes.getDrawable(R.drawable.indi_noti_brightness_panel_bg);
						        
						    }
						});
					}
					
					// indi_noti_volume_panel_bg (lg)
					resId = res.getIdentifier("indi_noti_volume_panel_bg", "drawable", pkg);
					if (resId != 0) {
					
						// troca
						res.setReplacement(resId, new XResources.DrawableLoader() {
							
						    @Override
						    public Drawable newDrawable(XResources res, int id) throws Throwable {
						    	
						    	// utiliza o indi_noti_brightness_panel_bg
						        return modRes.getDrawable(R.drawable.indi_noti_brightness_panel_bg);
						        
						    }
						});
					}
				}
			}
			
		} catch (Exception e) {
			
			XposedBridge.log(e);
			
		}
	}
}