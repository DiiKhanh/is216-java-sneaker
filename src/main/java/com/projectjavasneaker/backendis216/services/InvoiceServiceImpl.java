package com.projectjavasneaker.backendis216.services;

import com.projectjavasneaker.backendis216.models.Invoices;
import com.projectjavasneaker.backendis216.repository.InvoiceRepository;

//import com.projectjavasneaker.backendis216.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class InvoiceServiceImpl implements InvoiceService{


    @Autowired
    InvoiceRepository invoiceRepository;

    @Override
    public List<Invoices> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    

    @Override
    public Optional<Invoices> getInvoice(Long id){
        return invoiceRepository.findById(id);
    }



//	@Override
//	public Invoices addNewInvoices(Invoices invoice) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}

//@Override
//public Post updatePost(long id, Post postRequest) {
//	Post post = postRepository.findById(id)
//			.orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
//	
//	post.setTitle(postRequest.getTitle());
//	post.setDescription(postRequest.getDescription());
//	post.setContent(postRequest.getContent());
//	return postRepository.save(post);
//}
//
//@Override
//public void deletePost(long id) {
//	Post post = postRepository.findById(id)
//			.orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
//	
//	postRepository.delete(post);
//}
//
//@Override
//public Post getPostById(long id) {
//	Optional<Post> result = postRepository.findById(id);
//	if(result.isPresent()) {
//		return result.get();
//	}else {
//		throw new ResourceNotFoundException("Post", "id", id);
//	}
//	
////	Post post = postRepository.findById(id)
////			.orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
//	//return post;
//}
//}
