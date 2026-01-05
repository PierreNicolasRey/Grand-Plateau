process.env.CHROME_BIN = 'C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe';

module.exports = function (config) {
  config.set({
    frameworks: ['jasmine', '@angular-devkit/build-angular'],
        files: [],
        exclude: [],
        preprocessors: {
        'src/**/*.js': ['coverage'] // Cette ligne est souvent gérée par @angular-devkit/build-angular dans Angular
        },
    reporters: ['progress', 'kjhtml', 'coverage'],
    coverageReporter: {
        dir: require('path').join(__dirname, './coverage'),
        reports: ['html', 'lcovonly', 'text-summary'], 
        fixWebpackSourcePaths: true,
        reporters: [
            { type: 'html', subdir: 'html' },
            { type: 'lcovonly', subdir: '.', file: 'lcov.info' },
            { type: 'text-summary' }
        ]
    },
        client: {
      jasmine: {
      },
      clearContext: false
    },
    jasmineHtmlReporter: {
      suppressAll: true 
    },
    port: 9876,
    logLevel: config.LOG_INFO,
    autoWatch: true,
    customLaunchers: {
      BraveHeadless: {
        base: 'Chrome',
        flags: [
          '--headless',
          '--disable-gpu',
          '--remote-debugging-port=%d',
          '--no-sandbox'
        ]
      }
    },
    browsers: ['BraveHeadless'], 
        singleRun: true,
        concurrency: Infinity
  });
};