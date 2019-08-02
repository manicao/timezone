package com.example.timezone.controller;

import com.example.timezone.models.EntityZone;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/api")
@Api(value = "Employee Management System", description = "Operation pertaining to employee in Employee Management System")
public class TimeZone {


    private static  final String DATE_FORMAT="HH:mm:ss";

    @ApiOperation(value = "Get hour in UTC format")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 400, message = "Something wrong"),
    })
    @PostMapping("/timezone")
    public ResponseEntity<Response> convertZoneUTC(
            @ApiParam(value = "Employee object store in database table", required = true)
            @Valid @RequestBody EntityZone reques){

        DateTimeFormatter fmt=new DateTimeFormatterBuilder()
                .append(DateTimeFormatter.ISO_LOCAL_TIME)
                .parseDefaulting(ChronoField.EPOCH_DAY,0)
                .toFormatter();
        LocalDateTime ldt=LocalDateTime.parse(reques.getTime(), fmt);

        ZoneId zoneId=ZoneId.of(reques.getTimezone());

        ZoneId zoneUTC=ZoneId.of("UTC");


        ZonedDateTime zonedDateTime=ldt.atZone(zoneId);

        ZonedDateTime zonedDateTimeUTC=zonedDateTime.withZoneSameInstant(zoneUTC);

        DateTimeFormatter format=DateTimeFormatter.ofPattern(DATE_FORMAT);

        EntityZone entityZone=new EntityZone(format.format(zonedDateTimeUTC),"utc");

        Response response=new Response();
        response.setResponse(entityZone);

        return ResponseEntity.ok(response);

    }
}


class Response{
    public EntityZone response;

    public Response() {
    }

    public EntityZone getResponse() {
        return response;
    }

    public void setResponse(EntityZone response) {
       this.response = response;
    }
}