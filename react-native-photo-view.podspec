require "json"

package = JSON.parse(File.read(File.join(__dir__, "package.json")))

Pod::Spec.new do |s|
  s.name         = "react-native-photo-view"
  s.version      = package["version"]
  s.summary      = package['description']
  s.author       = package['author']
  s.homepage     = package['homepage']
  s.license      = package['license']
  s.ios.deployment_target = "10.0"
  s.tvos.deployment_target = "10.0"
  s.source       = { :git => "https://github.com/alwx/react-native-photo-view.git", :tag => "v#{s.version}" }
  s.source_files = "ios/**/*.{h,m,mm,cpp}" # Include .mm files for Fabric

  # Dependencies
  s.dependency "React-Core"
  s.dependency "SDWebImage"
  s.dependency "SDWebImageWebPCoder"

  # Conditional Fabric-specific configuration
  if ENV['RCT_NEW_ARCH_ENABLED'] == '1'
    s.source_files    = 'ios/**/*.{h,m,mm,cpp}'
    install_modules_dependencies(s)
  end
end
