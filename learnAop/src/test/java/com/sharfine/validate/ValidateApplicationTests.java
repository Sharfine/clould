package com.sharfine.validate;

import cn.hutool.json.JSON;
import com.sharfine.validate.dao.HrDao;
import com.sharfine.validate.model.BaseBlock;
import com.sharfine.validate.model.EthBlock;
import com.sharfine.validate.model.Hr;
import com.sharfine.validate.model.MiningMachine;
import com.sharfine.validate.utils.PageUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import com.sharfine.validate.utils.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@SpringBootTest
class ValidateApplicationTests {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private HrDao hrDao;

    @Test
    void contextLoads() {
        Hr hr = hrDao.get();
        System.out.println(hr.toString());
    }

    @Test
    void query() {
        int pageSize = 30;
        int pageNum = -1;

        Query query = new Query();

        query.with(Sort.by("height"));

        PagingInfo<Long> longPagingInfo = PageUtil.mongoQuery(mongoTemplate,
                query,
                EthBlock.class,
                BaseBlock::getHeight,
                pageNum,
                pageSize
        );

        System.out.println(longPagingInfo);
    }

    @Test
    void context() throws IOException {
        List<MiningMachine> all = mongoTemplate.findAll(MiningMachine.class);
        String filePath = "C:\\Users\\sharfine\\Desktop\\机器信息.xlsx";
        XSSFWorkbook wookbook = new XSSFWorkbook(new FileInputStream(filePath));

        XSSFSheet sheet = wookbook.getSheet("Sheet1");

        //获取到Excel文件中的所有行数

        int rows = sheet.getPhysicalNumberOfRows();

        //遍历行

        List<MiningMachine> list = new ArrayList<>();

        for (int i = 1; i < rows; i++) {

            // 读取左上端单元格

            XSSFRow row = sheet.getRow(i);

            // 行不为空

            if (row != null) {
                MiningMachine machine = new MiningMachine();

                //获取到Excel文件中的所有的列

                int cells = row.getPhysicalNumberOfCells();

                //城市

                XSSFCell cityCell = row.getCell(1);

                String city = getValue(cityCell);

                //机房

                XSSFCell roomCell = row.getCell(2);

                String room = getValue(roomCell);

                //机柜

                XSSFCell CabinetCell = row.getCell(3);

                String cabinet = getValue(CabinetCell);

                //机器
                XSSFCell machineCell = row.getCell(4);

                String machineName = getValue(machineCell);

                //机器码
                XSSFCell machineIdCell = row.getCell(5);

                String machineId = getValue(machineIdCell);
                machineId = machineId.substring(0, machineId.indexOf("."));

                //cpu
                XSSFCell cpuCell = row.getCell(6);

                String cpu = getValue(cpuCell);

                //cpuType
                XSSFCell cpuTypeCell = row.getCell(7);

                String cpuType = getValue(cpuTypeCell);

                //ram
                XSSFCell ramCell = row.getCell(8);

                String ram = getValue(ramCell);

                //motherboard
                XSSFCell motherboardCell = row.getCell(9);

                String motherboard = getValue(motherboardCell);

                //gpu
                XSSFCell gpuCell = row.getCell(10);

                String gpu = getValue(gpuCell);

                //storage
                XSSFCell storageCell = row.getCell(11);

                String storage = getValue(storageCell);

                //networkCard
                XSSFCell networkCardCell = row.getCell(12);

                String networkCard = getValue(networkCardCell);

                //Os
                XSSFCell osCell = row.getCell(13);

                String os = getValue(osCell);
                machine.setCabinet(cabinet);
                machine.setCity(city);
                machine.setCpu(cpu);
                machine.setCreateTime(new Date());
                machine.setMachineId(machineId);
                machine.setMachineName(machineName);
                machine.setMotherboard(motherboard);
                machine.setNetworkCard(networkCard);
                machine.setOs(os);
                machine.setStorage(storage);
                machine.setRoomName(room);
                machine.setRam(ram);
                machine.setGpu(gpu);
                machine.setUpdateTime(new Date());
                machine.setCpuType(cpuType);

                String id = UUID.randomUUID().toString();
                machine.setId(id.replace("-", ""));
                mongoTemplate.save(machine);


                //list.add(machine);

            }

        }

        //System.out.println(list.get(0));

    }

    private String getValue(XSSFCell xSSFCell) {

        if (null == xSSFCell) {

            return "";

        }

        if (xSSFCell.getCellType() == xSSFCell.CELL_TYPE_BOOLEAN) {

            // 返回布尔类型的值

            return String.valueOf(xSSFCell.getBooleanCellValue());

        } else if (xSSFCell.getCellType() == xSSFCell.CELL_TYPE_NUMERIC) {

            // 返回数值类型的值

            return String.valueOf(xSSFCell.getNumericCellValue());

        } else {

            // 返回字符串类型的值

            return String.valueOf(xSSFCell.getStringCellValue());

        }

    }

}
