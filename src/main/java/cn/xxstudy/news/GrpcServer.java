package cn.xxstudy.news;

import cn.xxstudy.news.service.NewsService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * @date: 2023/3/27 20:59
 * @author: TangRen
 * @remark:
 */
public class GrpcServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(9090).addService(new NewsService()).build();
        server.start();
        System.out.println("server start success ....");
        server.awaitTermination();
    }
}
