package com.sunrise.vertx.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.redis.RedisClient;

import java.util.HashMap;
import java.util.HashSet;

public class SimpleApplicationVerticle extends AbstractVerticle {
    /**
     * 配置
     */
    private static final String HTTP_HOST = "0.0.0.0";
    public static final Integer HTTP_PORT = 8888;
    public static final String REDIS_HOST = "127.0.0.1";
    public static final Integer REDIS_PORT = 6379;

    private RedisClient redisClient;


    @Override
    public void start(Future<Void> startFuture) throws Exception {
        //run start

        //init redis repo data

        //router
        Router router = Router.router(vertx);

        //CORS support
        HashSet<String> allowHeaders = new HashSet<>();
        allowHeaders.add("x-requested-with");
        allowHeaders.add("Access-Control-Allow-Origin");
        allowHeaders.add("origin");
        allowHeaders.add("Content-Type");
        allowHeaders.add("accept");
        HashSet<HttpMethod> allowMethods = new HashSet<>();
        allowMethods.add(HttpMethod.GET);
        allowMethods.add(HttpMethod.POST);
        allowMethods.add(HttpMethod.DELETE);
        allowMethods.add(HttpMethod.PATCH);

        router.route().handler(
                CorsHandler.create("*")
                .allowedHeaders(allowHeaders)
                .allowedMethods(allowMethods));
        router.route().handler(BodyHandler.create());

        //create server
        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(HTTP_PORT,HTTP_HOST,result->{
                    if (result.succeeded()){
                        startFuture.complete();
                    }else {
                        startFuture.fail(result.cause());
                    }
                });

    }
}
