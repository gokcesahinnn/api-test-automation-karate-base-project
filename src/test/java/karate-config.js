function fn(){

   var env = karate.env; // get java system property 'karate.env'

    karate.log('karate.env system property was:', env);

   if (!env) {
          env = ''; // env list : beta, prod
      }

   var config = { // base config JSON
        baseUrl: 'https://restful-booker.herokuapp.com/booking',
        graphQlBaseUrl: 'https://graphql.anilist.co',
        featuresPath: 'classpath:features',
        dataPath: 'classpath:data',
        modelPath: 'classpath:model',
        callerPath: 'classpath:callers',
        graphqlPath: 'classpath:model/graphql',
   };

   karate.configure('connectTimeout', 25000);
   karate.configure('readTimeout', 25000);
   return config;
}