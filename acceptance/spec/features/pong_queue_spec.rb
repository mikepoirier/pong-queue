require_relative '../spec_helper'

describe 'pong queue api' do
  before :each do
    delete pong_queue_url('/pong/queue')
  end
  
  describe 'GET /pong/queue' do
    it 'should return a 200 response code' do
      response = HTTParty.get(pong_queue_url('/pong/queue'))

      expect(response.code).to eq 200
    end

    it 'returns a pong queue response' do
      actual_response = get pong_queue_url('/pong/queue')

      expected_response = {
        position: -1,
        queue: []
      }

      expect(actual_response).to eq expected_response
    end

    context 'when items have been added to the queue' do
      before :each do
        (1..3).each do |n|
          request = {
            name: "name #{n}",
            team: "team #{n}"
          }
          post(pong_queue_url('/pong/queue'), request)
        end

        @response = get pong_queue_url('/pong/queue')
      end

      it 'returns a queue response with all queue items' do
        expected_queue = [
          {name: 'name 1', team: 'team 1'},
          {name: 'name 2', team: 'team 2'},
          {name: 'name 3', team: 'team 3'}
        ]

        expect(@response[:queue]).to eq expected_queue
      end
    end
  end

  describe 'POST /pong/queue' do
    before :each do
      request = {
        name: 'Mike Poirier',
        team: 'Evaluators'
      }
      @response = post(pong_queue_url('/pong/queue'), request)
    end

    it 'returns queue response with position 1' do
      expect(@response[:position]).to eq 1
    end

    it 'returns queue response with response in queue' do
      queue_member = {
        name: 'Mike Poirier',
        team: 'Evaluators'
      }
      expect(@response[:queue]).to include queue_member
    end

    context 'when adding another queue entry' do
      before :each do
        request2 = {
          name: 'name 2',
          team: 'team 2'
        }
        @response = post(pong_queue_url('/pong/queue'), request2)
      end

      it 'returns the position incremented by one' do
        expect(@response[:position]).to eq 2
      end

      it 'returns the queue with the new request added to the bottom' do
        expected_queue = [
          {name: 'Mike Poirier', team: 'Evaluators'},
          {name: 'name 2', team: 'team 2'}
        ]
        expect(@response[:queue]).to eq expected_queue
      end
    end
  end

  describe 'DELETE /pong/queue' do
    before :each do
      (1..10).each do |n|
        request = {
          name: "name #{n}",
          team: "team #{n}"
        }
        post(pong_queue_url('/pong/queue'), request)
      end
      delete(pong_queue_url('/pong/queue'))
    end
    it 'deletes the entire queue' do
      response = get(pong_queue_url('/pong/queue'))

      expected = {
        position: -1,
        queue: []
      }

      expect(response).to eq expected
    end
  end
end