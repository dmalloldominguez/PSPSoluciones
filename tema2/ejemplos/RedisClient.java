import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.args.ListPosition;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class RedisClient {
    public static void main(String[] args) throws IOException {
        Jedis jedis = new Jedis("ec2-44-202-218-229.compute-1.amazonaws.com", 6000);

        redisGetSetValues(jedis);
        redisList(jedis);
        redisHash(jedis);
        redisWildCard(jedis);

        jedis.close();
    }

    public static void redisGetSetValues(Jedis jedis) {
        // SET and GET
        jedis.set("foo", "bar");
        String value = jedis.get("foo");
        System.out.println(value);

        // MSET and MGET
        jedis.mset("a", "10", "b", "20", "c", "30");
        List<String> values = jedis.mget("a", "b", "c");
        for (String v : values) {
            System.out.println(v);
        }

        jedis.del("foo", "a", "b", "c");
    }

    public static void redisList(Jedis jedis) {
        jedis.del("notas:dam");

        // LPUSH, RPUSH, RPOP, LINSERT and LSET
        jedis.rpush("notas:dam", "1");
        System.out.println("Inserto 1");
        jedis.rpush("notas:dam", "2");
        System.out.println("Inserto 2");
        jedis.rpush("notas:dam", "3");
        System.out.println("Inserto 3");
        String aux = jedis.rpop("notas:dam");
        System.out.printf("Saco %s del final\r\n", aux);
        jedis.linsert("notas:dam", ListPosition.BEFORE, "2", "1.5");
        System.out.println("Inserto 1.5 antes del 2");
        jedis.rpush("notas:dam", "4");
        System.out.println("Inserto 4");
        jedis.lpush("notas:dam", "0");
        System.out.println("Inserto 0 al principio");
        jedis.lpush("notas:dam", "-1");
        System.out.println("Inserto -1 al principio");
        aux = jedis.lpop("notas:dam");
        System.out.printf("Saco %s del final\r\n", aux);

        // Obtain all the values of the lists
        List<String> values2 = jedis.lrange("notas:dam", 0, -1);

        // Print the list
        for (String v : values2) {
            System.out.println(v);
        }

        jedis.del("notas:dam");
    }

    public static void redisHash(Jedis jedis) {
        // Delete the key to avoid unexpected results
        jedis.del("estudiantes:dam2");

        // HSET
        jedis.hset("estudiantes:dam2", "Laura", "1");
        jedis.hset("estudiantes:dam2", "Pau", "2");
        jedis.hset("estudiantes:dam2", "Carlos", "3");
        jedis.hset("estudiantes:dam2", "Aitor", "4");
        jedis.hset("estudiantes:dam2", "Santi", "5");
        jedis.hset("estudiantes:dam2", "Flavius", "6");

        // HKEYS
        Set<String> keys = jedis.hkeys("estudiantes:dam2");

        // Print the results
        for (String c : keys) {
            System.out.println(c + ": " + jedis.hget("estudiantes:dam2", c));
        }

        // Delete the key created
        jedis.del("user:mtorres");
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
}