#import <UIKit/UIKit.h>
#import "React/RCTBridgeModule.h"

@interface ReactNativeShareExtension : UIViewController<RCTBridgeModule>
- (UIView*) shareView;
@property (nonatomic, strong) UIWindow *window;
@end
