package com.bjs.java8.part10;

import java.util.Optional;

/**
 * @Description
 * @Author BianJiashuai
 */
public class Demo01_Optional {

    public static void main(String[] args) {
        // Optional.empty ，创建一个空的 Optional对象
        Optional<Car> empty = Optional.empty();

        // Optional.of ，依据一个非空值创建一个 Optional 对象
        // 如果 car 是一个 null ，这段代码会立即抛出一个NullPointerException
        Optional<Car> car = Optional.of(new Car());

        // Optional.ofNullable ，你可以创建一个允许 null 值的 Optional 对象
        Optional<Car> car1 = Optional.ofNullable(new Car());
        Optional<Car> car2 = Optional.ofNullable(null);

    }

    public String getCarInsuranceName(Person person) {
        Optional<Person> optPerson = Optional.of(person);
        Optional<String> name =
                optPerson.flatMap(Person::getCar)
                        .flatMap(Car::getInsurance)
                        .map(Insurance::getName);
        return name.orElse("UnKnown");
    }

    // 使用null-安全版本（不抛出NullPointException）找出最便宜的保险公司
    public Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> person, Optional<Car> car) {
        // 普通的写法，但是该方法的具体实现和你之前曾经实现的 null 检查太相似了
        if (person.isPresent() && car.isPresent()) {
            return Optional.of(findCheapestInsurance(person.get(), car.get()));
        } else {
            return Optional.empty();
        }
    }

    // 上面方法更优雅的写法
    public Optional<Insurance> nullSafeFindCheapestInsuranceGraceFully(
            Optional<Person> person, Optional<Car> car) {
        // flatMap和map 有ifPresent的判断，如果为空，Lambda表达式的内容就不会执行
        return person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));
    }

    public Insurance findCheapestInsurance(Person person, Car car) {
        // 不同的保险公司提供的查询服务
        // 对比所有数据
        Insurance cheapestCompany = new Insurance();    // 模拟找到的保险公司
        return cheapestCompany;
    }


    // 找出年龄大于或者等于 minAge 参数的 Person 所对应的保险公司名称
    public String getCarInsuranceName(Optional<Person> person, int minAge) {
        return person.filter(p -> p.getAge() >= minAge)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("UnKnown");
    }
}


// 以下为Optional模型演示
class Person {
    private Optional<Car> car;
    private int age;

    public int getAge() {
        return age;
    }

    public Optional<Car> getCar() {
        return car;
    }
}

class Car {
    private Optional<Insurance> insurance;

    public Optional<Insurance> getInsurance() {
        return insurance;
    }
}

class Insurance {
    private String name;

    public String getName() {
        return name;
    }
}
