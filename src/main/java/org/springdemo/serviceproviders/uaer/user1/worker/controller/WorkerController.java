package org.springdemo.serviceproviders.uaer.user1.worker.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdemo.serviceproviders.uaer.user1.worker.dto.WorkerRequest;
import org.springdemo.serviceproviders.uaer.user1.worker.dto.WorkerResponse;
import org.springdemo.serviceproviders.uaer.user1.worker.entity.Worker;
import org.springdemo.serviceproviders.uaer.user1.worker.service.WorkerService;
import org.springdemo.serviceproviders.job.dtos.JobResponse;
import org.springdemo.serviceproviders.order.dtos.OrderResponse;
import org.springdemo.serviceproviders.order.enums.Status;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/worker")
@RequiredArgsConstructor
public class WorkerController {

    private final WorkerService workerService;

    @PostMapping
    public WorkerResponse create(@Valid @RequestBody WorkerRequest workerRequest){
        return workerService.create(workerRequest);
    }

    @GetMapping("/{id}")
    public WorkerResponse findById(@PathVariable Integer id){
        return workerService.findById(id);
    }

    @GetMapping
    public List<WorkerResponse> FindAll(){
        return workerService.FindAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        workerService.delete(id);
    }

    @PutMapping
    public WorkerResponse update(@Valid @RequestBody WorkerRequest workerRequest){

        return workerService.update(workerRequest);

    }


//    ---------------------------------------------------------

    @GetMapping("{id}/job")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<JobResponse> findAllJobByWorkerId(@PathVariable Integer id){
        return workerService.findAllJobByWorkerId(id);

    }


//    -------------------------------------------------------------




    @GetMapping("request")
    public List<OrderResponse> findAllRequestByWorker(@AuthenticationPrincipal Worker worker){

        return workerService.findAllRequestByWorker(worker);

    }

    @PutMapping("status/{id}")
    public Status changeStatus(@RequestParam Status status ,@PathVariable Integer id){

       return workerService.changeStatus(status, id);


    }


    @GetMapping("status")
    public List<OrderResponse> findAllByStatus(@RequestBody Status status
                                          , @AuthenticationPrincipal Worker worker){

        return workerService.findAllByStatus(status, worker);

    }
}
