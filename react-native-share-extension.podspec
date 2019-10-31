Pod::Spec.new do |s|
  s.name         = "react-native-share-extension"
  s.version      = "1.0.0"
  s.summary      = "-"

  s.homepage     = "https://github.com/alinz/react-native-share-extension"

  s.license      = "MIT"
  s.authors      = "Alessandro"
  s.ios.deployment_target = '8.0'
  s.tvos.deployment_target = '10.0'

  s.source       = { :git => "https://github.com/alinz/react-native-share-extension" }

  s.source_files  = "ios/*.{h,m}"
  
  s.dependency 'React'
end
