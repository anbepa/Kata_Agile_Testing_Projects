function fn() {

  var config = {
    baseUrl: karate.properties['baseUrl'] || 'https://opensource-demo.orangehrmlive.com'
  };
  return config;
}