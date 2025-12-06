function fn() {
  // Karate configuration file.  You can override the baseUrl by passing
  // -DbaseUrl=http://yourhost on the Maven command line.  If not
  // provided, the OrangeHRM demo site is used by default.
  var config = {
    baseUrl: karate.properties['baseUrl'] || 'https://opensource-demo.orangehrmlive.com'
  };
  return config;
}