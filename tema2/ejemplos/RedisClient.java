import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.args.ListPosition;
import redis.clients.jedis.resps.Tuple;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class RedisClient {
    public static void main(String[] args) throws IOException {
        Jedis jedis = new Jedis("52.201.217.100", 6000);

        redisGetSetValues(jedis);
        redisList(jedis);
        redisHash(jedis);
        redisWildCard(jedis);
        redisSet(jedis);
        redisSortedSet(jedis);

        jedis.close();
    }

    public static void redisGetSetValues(Jedis jedis) {
        jedis.set("foo", "bar");
        String value = jedis.get("foo");
        System.out.println(value);
        jedis.mset("a", "10", "b", "20", "c", "30");
        List<String> values = jedis.mget("a", "b", "c");
        for (String v : values) {
            System.out.println(v);
        }

        jedis.del("foo", "a", "b", "c");
    }

    public static void redisList(Jedis jedis) {
        jedis.del("listado");

        jedis.rpush("listado", "1");
        System.out.println("Inserto 1");
        jedis.rpush("listado", "2");
        System.out.println("Inserto 2");
        jedis.rpush("listado", "3");
        System.out.println("Inserto 3");
        String aux = jedis.rpop("listado");
        System.out.printf("Saco %s del final\r\n", aux);
        jedis.linsert("listado", ListPosition.BEFORE, "2", "1.5");
        System.out.println("Inserto 1.5 antes del 2");
        jedis.rpush("listado", "4");
        System.out.println("Inserto 4");
        jedis.lpush("listado", "0");
        System.out.println("Inserto 0 al principio");
        jedis.lpush("listado", "-1");
        System.out.println("Inserto -1 al principio");
        aux = jedis.lpop("listado");
        System.out.printf("Saco %s del final\r\n", aux);

        List<String> values2 = jedis.lrange("listado", 0, -1);

        for (String v : values2) {
            System.out.println(v);
        }

        jedis.del("listado");
    }

    public static void redisHash(Jedis jedis) {
        jedis.del("estudiantes:dam2");

        jedis.hset("estudiantes:dam2", "Laura", "1");
        jedis.hset("estudiantes:dam2", "Pau", "2");
        jedis.hset("estudiantes:dam2", "Carlos", "3");
        jedis.hset("estudiantes:dam2", "Aitor", "4");
        jedis.hset("estudiantes:dam2", "Santi", "5");
        jedis.hset("estudiantes:dam2", "Flavius", "6");

        Set<String> keys = jedis.hkeys("estudiantes:dam2");

        for (String c : keys) {
            System.out.println(c + ": " + jedis.hget("estudiantes:dam2", c));
        }

        jedis.del("user:dam2");
    }

    public static void redisWildCard(Jedis jedis) {
        jedis.del("letter:*");

        jedis.set("letter:a", "1");
        jedis.set("letter:b", "2");
        jedis.set("letter:c", "3");
        jedis.set("letter:d", "4");
        Set<String> keys = jedis.keys("letter:*");
        for (String c : keys) {
            System.out.println(c + "-> " + jedis.get(c));
        }
    }

    public static void redisSet(Jedis jedis) {
        jedis.del("estudiantes:dam1");
        jedis.del("estudiantes:dam2");

        jedis.sadd("estudiantes:dam1", "student1", "student2", "student3");
        jedis.sadd("estudiantes:dam2", "student3", "student4", "student5", "student6");
        jedis.srem("estudiantes:dam2", "student6");

        Set<String> totalStudents = jedis.sunion("estudiantes:dam1", "estudiantes:dam2");
        Set<String> commonStudents = jedis.sinter("estudiantes:dam1", "estudiantes:dam2");
        Set<String> studentsDam1 = jedis.sdiff("estudiantes:dam2", "estudiantes:dam1");

        // Print the union
        System.out.println("Estudiantes:");
        for (String s : totalStudents) {
            System.out.println(s);
        }

        // Print the intersection
        System.out.println("Estudiantes en DAM2 y DAM2:");
        for (String s : commonStudents) {
            System.out.println(s);
        }
        // Print the difference
        System.out.println("*** Estudiantes sólo en DAM1: ");
        for (String s : studentsDam1) {
            System.out.println(s);
        }

        // Delete the keys created
        jedis.del("estudiantes:dam1");
        jedis.del("estudiantes:dam2");
    }

    public static void redisSortedSet(Jedis jedis) {
        jedis.del("notas:dam2");

        // ZADD
        jedis.zadd("notas:dam2", 9, "student1");
        jedis.zadd("notas:dam2", 3, "student2");
        jedis.zadd("notas:dam2", 8, "student3");

        jedis.zincrby("notas:dam2", 1, "student2");

        long numberOfPassStudents = jedis.zcount("notas:dam2", 5, 10);

        List<Tuple> passedStudents = jedis.zrangeByScoreWithScores("notas:dam2", 5, 10);

        // Print the results
        System.out.println("*** Number of passed students: " + numberOfPassStudents);

        for (Tuple s: passedStudents) {
            System.out.println(s.getElement() + " " + s.getScore());
        }

        jedis.del("scores:ggvd");
    }
}